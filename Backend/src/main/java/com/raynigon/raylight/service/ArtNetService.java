package com.raynigon.raylight.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.raynigon.raylight.model.ArtNetDMXOutput;
import com.raynigon.raylight.model.DMXUniverse;
import com.raynigon.raylight.model.artnet.ArtDmxPacket;
import com.raynigon.raylight.model.artnet.PacketType;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArtNetService {

    private InetAddress address;
    private DatagramSocket socket;
    private List<ArtNetDMXOutput> outputs = new ArrayList<>();
    private Map<Integer, Long> sequenceIds = new HashMap<>();
    private Map<Integer, SendArtNetPackage> lastData = new HashMap<>();

    @SneakyThrows
    public ArtNetService() {
        socket = new DatagramSocket(6454);
    }

    public void addOutput(ArtNetDMXOutput output) {
        outputs.add(output);
    }

    @Scheduled(fixedRate = 20)
    public void handleSendCycle() {
        if (outputs.isEmpty()) {
            return;
        }
        for (ArtNetDMXOutput output : outputs) {
            output.listUniverses().forEach(universe -> sendArtNetPackage(output.getInetAddress(), universe));
        }
    }

    private void sendArtNetPackage(InetAddress address, DMXUniverse universe) {
        ArtDmxPacket artNetPacket = PacketType.ART_OUTPUT.createPacket();
        artNetPacket.setUniverse(0, universe.getId());
        artNetPacket.setDMX(universe.toArtNet());
        if (wasAlreadySend(universe, artNetPacket))
            return;
        artNetPacket.setSequenceID(getNextSequenceId(universe));
        DatagramPacket datagramPacket = new DatagramPacket(artNetPacket.getData(), artNetPacket.getLength());
        datagramPacket.setAddress(address);
        datagramPacket.setPort(6454);
        try {
            socket.send(datagramPacket);
            lastData.put(universe.getId(), new SendArtNetPackage(artNetPacket, System.currentTimeMillis()));
        } catch (IOException e) {
            log.warn("Unable to send ArtNet Package for Universe {}", universe.getId(), e);
        }
    }

    private boolean wasAlreadySend(DMXUniverse universe, ArtDmxPacket artNetPacket) {
        if (!lastData.containsKey(universe.getId())) {
            return false;
        }
        SendArtNetPackage sendPackage = lastData.get(universe.getId());
        if (System.currentTimeMillis() - sendPackage.getTimestamp() > 250) {
            return false;
        }
        return Arrays.equals(sendPackage.getPayload(), artNetPacket.getDmxData());
    }

    private long getNextSequenceId(DMXUniverse universe) {
        if (sequenceIds.containsKey(universe.getId())) {
            long sequenceId = sequenceIds.get(universe.getId());
            sequenceId += 1;
            sequenceIds.put(universe.getId(), sequenceId);
            return sequenceId;
        }
        sequenceIds.put(universe.getId(), 0L);
        return 0L;
    }

    @Getter
    private class SendArtNetPackage {

        private long timestamp;

        private byte[] payload;

        public SendArtNetPackage(ArtDmxPacket artNetPacket, long timestamp) {
            this.timestamp = timestamp;
            this.payload = artNetPacket.getDmxData();
        }
    }
}

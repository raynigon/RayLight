package com.raynigon.raylight.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.raynigon.raylight.model.ArtNetDMXOutput;
import com.raynigon.raylight.model.DMXUniverse;
import com.raynigon.raylight.model.artnet.ArtDmxPacket;
import com.raynigon.raylight.model.artnet.PacketType;

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

    @SneakyThrows
    public ArtNetService(){
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

    @SneakyThrows
    private void sendArtNetPackage(InetAddress address, DMXUniverse universe) {
        ArtDmxPacket artNetPacket = PacketType.ART_OUTPUT.createPacket();
        artNetPacket.setSequenceID(getNextSequenceId(universe));
        artNetPacket.setUniverse(0, universe.getId());
        artNetPacket.setDMX(universe.toArtNet());
        DatagramPacket datagramPacket = new DatagramPacket(artNetPacket.getData(), artNetPacket.getLength());
        datagramPacket.setAddress(address);
        datagramPacket.setPort(6454);
        socket.send(datagramPacket);
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
}

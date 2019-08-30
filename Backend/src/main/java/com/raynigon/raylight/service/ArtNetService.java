package com.raynigon.raylight.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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

    private DatagramSocket socket;
    private List<DMXUniverse> universes = new ArrayList<>();
    private Map<Integer, Long> sequenceIds = new HashMap<>();

    @PostConstruct
    public void init() throws SocketException {
        socket = new DatagramSocket(6454);
        socket.setBroadcast(true);
    }

    @Scheduled(fixedRate = 10)
    public void handleSendCycle() {
        if (universes.isEmpty()) {
            return;
        }
        universes.stream().parallel().forEach(this::sendArtNetPackage);
    }

    @SneakyThrows
    private void sendArtNetPackage(DMXUniverse universe) {
        ArtDmxPacket artNetPacket = PacketType.ART_OUTPUT.createPacket();
        artNetPacket.setSequenceID(getNextSequenceId(universe));
        artNetPacket.setUniverse(0, universe.getId());
        artNetPacket.setDMX(universe.toArtNet());
        DatagramPacket datagramPacket = new DatagramPacket(artNetPacket.getData(), artNetPacket.getLength());
        datagramPacket.setAddress(InetAddress.getByName("255.255.255.255")); //TODO make this per Interface to make Routers forward this message
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

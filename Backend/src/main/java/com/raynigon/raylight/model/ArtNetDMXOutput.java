package com.raynigon.raylight.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArtNetDMXOutput implements DMXOutput {

    private final String name;
    private final InetAddress address;
    private List<DMXUniverse> universes = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    public InetAddress getInetAddress() {
        return address;
    }

    @Override
    public void addUniverse(DMXUniverse universe) {
        universes.add(universe);
    }

    @Override
    public void removeUniverse(DMXUniverse universe) {
        universes.remove(universe);
    }

    @Override
    public List<DMXUniverse> listUniverses() {
        return Collections.unmodifiableList(universes);
    }
}

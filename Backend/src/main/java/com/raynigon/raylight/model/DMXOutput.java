package com.raynigon.raylight.model;

import java.util.List;

public interface DMXOutput {

    String getName();

    void addUniverse(DMXUniverse universe);

    void removeUniverse(DMXUniverse universe);

    List<DMXUniverse> listUniverses();
}

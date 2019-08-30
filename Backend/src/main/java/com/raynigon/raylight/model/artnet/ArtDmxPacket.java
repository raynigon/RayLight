/*
 * This file is part of artnet4j.
 *
 * Copyright 2009 Karsten Schmidt (PostSpectacular Ltd.)
 *
 * artnet4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * artnet4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with artnet4j. If not, see <http://www.gnu.org/licenses/>.
 */

package com.raynigon.raylight.model.artnet;

import java.util.Arrays;

import com.raynigon.raylight.utils.ByteUtils;

import lombok.Getter;

@Getter
public class ArtDmxPacket extends ArtNetPacket {

    private static final int CHANNELS_PER_UNIVERSE = 512;
    private int sequenceID;
    private int subnetID;
    private int universeID;
    private byte[] dmxData;

    public ArtDmxPacket() {
        super(PacketType.ART_OUTPUT, new ByteUtils(new byte[18 + CHANNELS_PER_UNIVERSE]));
        setHeader();
        setProtocol();
        data.setInt8(0x02, 13);
    }

    public void setDMX(byte[] dmxData) {
        if(dmxData==null || dmxData.length != 512){
            throw new IllegalArgumentException("DMX Data is not valid");
        }
        this.dmxData = Arrays.copyOf(dmxData, CHANNELS_PER_UNIVERSE);
        data.setByteChunk(dmxData, 18, CHANNELS_PER_UNIVERSE);
        data.setInt16(CHANNELS_PER_UNIVERSE, 16);
    }

    public void setSequenceID(long id) {
        sequenceID = (int) (id % 0xff);
        data.setInt8(sequenceID, 12);
    }

    /**
     * @param subnetID the subnetID to set
     */
    public void setSubnetID(int subnetID) {
        this.subnetID = subnetID & 0x0f;
    }

    /**
     * @param universeID the universeID to set
     */
    public void setUniverseID(int universeID) {
        this.universeID = universeID & 0x0f;
    }

    public void setUniverse(int subnetID, int universeID) {
        this.subnetID = subnetID & 0x0f;
        this.universeID = universeID & 0x0f;
        data.setInt16LE(subnetID << 4 | universeID, 14);
    }


}
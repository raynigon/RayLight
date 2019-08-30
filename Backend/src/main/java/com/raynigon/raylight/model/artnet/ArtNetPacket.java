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

import com.raynigon.raylight.utils.ByteUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ArtNetPacket {

    public static final byte[] HEADER = "Art-Net\0".getBytes();
    public static final int PROTOCOL_VERSION = 14;

    protected final PacketType type;
    protected final ByteUtils data;


    public byte[] getData() {
        return data.getBytes();
    }

    /**
     * Sets the header bytes of the packet consisting of {@link #HEADER} and the
     * type's OpCode.
     */
    void setHeader() {
        data.setByteChunk(HEADER, 0, 8);
        data.setInt16LE(type.getOpCode(), 8);
    }

    void setProtocol() {
        data.setInt16(PROTOCOL_VERSION, 10);
    }


    @Override
    public String toString() {
        return data.toHex();
    }

    public int getLength() {
        return data.length;
    }
}

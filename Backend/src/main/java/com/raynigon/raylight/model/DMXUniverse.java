package com.raynigon.raylight.model;

public class DMXUniverse {

    public static final int CHANNELS_IN_UNIVERSE = 512;
    private int id;
    private int[] channels;

    public DMXUniverse(int id){
        this.id = id;
        channels = new int[CHANNELS_IN_UNIVERSE];
    }

    public int getId() {
        return id;
    }

    public void setValue(int channel, int value){
        if (channel < 0 || value < 0 || channel >= CHANNELS_IN_UNIVERSE || value > 255){
            throw new IllegalArgumentException("Value out of Range");
        }
        channels[channel] = value;
    }

    public byte[] toArtNet(){
        byte[] data = new byte[CHANNELS_IN_UNIVERSE];
        for (int i=0;i<CHANNELS_IN_UNIVERSE;i++){
            data[i] = (byte) channels[i];
        }
        return data;
    }
}

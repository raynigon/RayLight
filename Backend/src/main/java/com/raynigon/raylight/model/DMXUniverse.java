package com.raynigon.raylight.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import lombok.Data;

import org.springframework.context.annotation.DependsOn;

public class DMXUniverse {

    public static final int CHANNELS_IN_UNIVERSE = 512;

    private final UniverseMetaData information;

    private MaxSupplier[] channels;

    public DMXUniverse(UniverseMetaData information){
        this.information = information;
        channels = new MaxSupplier[CHANNELS_IN_UNIVERSE];
        for (int i=0;i<CHANNELS_IN_UNIVERSE;i++){
            channels[i] = new MaxSupplier();
        }
    }

    public int getId() {
        return information.getId();
    }

    @Deprecated
    public void setValue(int channel, int value){
        if (channel < 0 || value < 0 || channel >= CHANNELS_IN_UNIVERSE || value > 255){
            throw new IllegalArgumentException("Value out of Range");
        }
        this.addSupplier(channel, ()->value);
    }

    public void addSupplier(int channel, Supplier<Integer> value){
        if (channel < 0 || channel >= CHANNELS_IN_UNIVERSE){
            throw new IllegalArgumentException("Value out of Range");
        }
        channels[channel].addSupplier(value);
    }

    public void removeSupplier(int channel, Supplier<Integer> value){
        if (channel < 0 || channel >= CHANNELS_IN_UNIVERSE){
            throw new IllegalArgumentException("Value out of Range");
        }
        channels[channel].removeSupplier(value);
    }

    public byte[] toArtNet(){
        byte[] data = new byte[CHANNELS_IN_UNIVERSE];
        for (int i=0;i<CHANNELS_IN_UNIVERSE;i++){
            data[i] = channels[i].get().byteValue();
        }
        return data;
    }

    public static class MaxSupplier implements Supplier<Integer> {

        private List<Supplier<Integer>> suppliers = new ArrayList<>();

        public void addSupplier(Supplier<Integer> supplier){
            if(suppliers.contains(supplier)){
                return;
            }
            suppliers.add(supplier);
        }

        public void removeSupplier(Supplier<Integer> supplier){
            suppliers.remove(supplier);
        }

        @Override
        public Integer get() {
            return suppliers.stream().mapToInt(Supplier::get).max().orElse(0);
        }
    }
}

package com.raynigon.raylight.model;

import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CueAction implements Supplier<Integer> {

    private int universe;

    private int channel;

    private int value;

    @Override
    public Integer get() {
        return value;
    }
}

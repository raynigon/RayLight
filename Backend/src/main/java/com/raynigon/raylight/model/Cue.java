package com.raynigon.raylight.model;

import com.raynigon.raylight.service.DMXUniverseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cue {

    private CueMetaData metaData;

    private DMXUniverseService universeService;

    public int getId(){
        return metaData.getId();
    }

    public void stop() {
        for (CueAction cueAction : metaData.getActions()){
            universeService.getUniverse(cueAction.getUniverse()).removeSupplier(cueAction.getChannel(), cueAction);
        }
    }

    public void play() {
        for (CueAction cueAction : metaData.getActions()){
            universeService.getUniverse(cueAction.getUniverse()).addSupplier(cueAction.getChannel(), cueAction);
        }
    }
}

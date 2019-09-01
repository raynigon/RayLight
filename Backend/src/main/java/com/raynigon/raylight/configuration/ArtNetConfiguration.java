package com.raynigon.raylight.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.raynigon.raylight.model.ArtNetDMXOutput;
import com.raynigon.raylight.model.DMXUniverse;
import com.raynigon.raylight.model.config.ArtNetConfigProperties;
import com.raynigon.raylight.repository.UniverseMetaDataRepository;
import com.raynigon.raylight.service.ArtNetService;
import com.raynigon.raylight.service.DMXUniverseService;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ArtNetConfigProperties.class)
public class ArtNetConfiguration {

    private final ArtNetConfigProperties properties;
    private final ArtNetService service;
    private final DMXUniverseService universeService;
    private List<DMXUniverse> universes = new ArrayList<>();
    private int i = 0;

    @PostConstruct
    public void init() {
        properties.getOutputs().stream().map(props -> {
            ArtNetDMXOutput output = new ArtNetDMXOutput(props.getName(), props.getAddress());
            universeService.getAllForOutput(output.getName()).forEach(output::addUniverse);
            universes.addAll(output.listUniverses());
            return output;
        }).forEach(service::addOutput);
    }

    /**
     * Dummy Logic for testing the ArtNet Interface
     * */
    //@Scheduled(fixedRate = 10, initialDelay = 1000)
    public void updateUniverse() {
        if(universes.isEmpty()){
            return;
        }
        DMXUniverse universe = universes.get(0);
        double x = i / 200.0;
        universe.setValue(0, getValue(x, 1.5));
        universe.setValue(1, getValue(x, 1));
        universe.setValue(2, getValue(x, 0.5));
        universe.setValue(3, getValue(x , 0));
        i++;
    }

    private int getValue(double x, double offset) {
        return (int) Math.round((Math.sin((x*2 + offset - 0.5) * Math.PI) + 1) / (2) * 255);
    }
}

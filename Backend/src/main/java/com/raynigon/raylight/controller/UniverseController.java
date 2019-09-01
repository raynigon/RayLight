package com.raynigon.raylight.controller;

import java.util.List;

import com.raynigon.raylight.model.DMXUniverse;
import com.raynigon.raylight.model.UniverseMetaData;
import com.raynigon.raylight.repository.UniverseMetaDataRepository;
import com.raynigon.raylight.service.DMXUniverseService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/universe")
public class UniverseController {

    private final DMXUniverseService universeService;
    private final UniverseMetaDataRepository repository;

    @PostMapping
    public UniverseMetaData create(@RequestBody UniverseMetaData dmxInfo) {
        return repository.save(dmxInfo);
    }

    @PutMapping
    public UniverseMetaData update(@RequestBody UniverseMetaData dmxInfo) {
        return repository.save(dmxInfo);
    }

    @GetMapping("{id}")
    public UniverseMetaData get(@PathVariable("id") int id) {
        return repository.findById(id).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<UniverseMetaData> list() {
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

    @PostMapping("{id}/dmx/channel/{channel}/{value}")
    public void create(@PathVariable("id") int id, @PathVariable("channel") int channel, @PathVariable("value") int value) {
        DMXUniverse universe = universeService.getUniverse(id);
        if(universe == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        universe.setValue(channel, value);
    }
}

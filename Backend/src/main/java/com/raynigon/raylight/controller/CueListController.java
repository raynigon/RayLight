package com.raynigon.raylight.controller;

import java.util.List;

import com.raynigon.raylight.model.CueListMetaData;
import com.raynigon.raylight.model.DMXUniverse;
import com.raynigon.raylight.model.UniverseMetaData;
import com.raynigon.raylight.repository.CueListRepository;
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
@RequestMapping("/api/cuelist")
public class CueListController {

    private final CueListRepository repository;

    @PostMapping
    public CueListMetaData create(@RequestBody CueListMetaData cueList) {
        return repository.save(cueList);
    }

    @PutMapping
    public CueListMetaData update(@RequestBody CueListMetaData cueList) {
        return repository.save(cueList);
    }

    @GetMapping("{id}")
    public CueListMetaData get(@PathVariable("id") int id) {
        return repository.findById(id).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<CueListMetaData> list() {
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

    @PostMapping("{cuelist}/cue")
    public CueListMetaData createCue(@RequestBody CueListMetaData cueList) {
        return repository.save(cueList);
    }

    @PutMapping("{cuelist}/cue")
    public CueListMetaData updateCue(@RequestBody CueListMetaData cueList) {
        return repository.save(cueList);
    }

    @GetMapping("{cuelist}/cue/{id}")
    public CueListMetaData getCue(@PathVariable("id") int id) {
        return repository.findById(id).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{cuelist}/cue")
    public List<CueListMetaData> listCues() {
        return repository.findAll();
    }

    @DeleteMapping("{cuelist}/cue/{id}")
    public void deleteCue(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

}

package com.raynigon.raylight.controller;

import java.util.List;

import com.raynigon.raylight.model.CueListMetaData;
import com.raynigon.raylight.model.CueMetaData;
import com.raynigon.raylight.repository.CueListRepository;
import com.raynigon.raylight.repository.CueRepository;

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
public class CueController {

    private final CueListRepository cuelistsRepository;
    private final CueRepository repository;

    @PostMapping("{cuelist}/cue")
    public CueMetaData createCue(@PathVariable("cuelist") int cueListId, @RequestBody CueMetaData cue) {
        CueListMetaData cueList = cuelistsRepository.findById(cueListId).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
        cue.setCuelist(cueList);
        return repository.save(cue);
    }

    @PutMapping("{cuelist}/cue")
    public CueMetaData updateCue(@PathVariable("cuelist") int cueListId, @RequestBody CueMetaData cue) {
        CueListMetaData cueList = cuelistsRepository.findById(cueListId).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
        cue.setCuelist(cueList);
        return repository.save(cue);
    }

    @GetMapping("{cuelist}/cue/{id}")
    public CueMetaData getCue(@PathVariable("id") int id) {
        return repository.findById(id).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{cuelist}/cue/{id}")
    public void deleteCue(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

}

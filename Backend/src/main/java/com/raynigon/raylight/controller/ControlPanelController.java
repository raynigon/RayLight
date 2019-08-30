package com.raynigon.raylight.controller;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.raynigon.raylight.model.ControlPanel;
import com.raynigon.raylight.repository.ControlPanelRepository;

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
@RequestMapping("/api/controlpanel")
public class ControlPanelController {

    private final ControlPanelRepository repository;

    @PostMapping
    public ControlPanel create(@RequestBody ControlPanel controlPanel) {
        controlPanel.setId(UUID.randomUUID().toString());
        controlPanel.setChanged(ZonedDateTime.now());
        repository.save(controlPanel);
        return controlPanel;
    }

    @PutMapping
    public ControlPanel update(@RequestBody ControlPanel controlPanel) {
        controlPanel.setChanged(ZonedDateTime.now());
        return repository.save(controlPanel);
    }

    @GetMapping("{id}")
    public ControlPanel get(@PathVariable("id") String id) {
        return repository.findById(id).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<ControlPanel> list() {
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}

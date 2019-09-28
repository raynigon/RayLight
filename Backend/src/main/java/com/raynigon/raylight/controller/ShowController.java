package com.raynigon.raylight.controller;

import com.raynigon.raylight.model.CueListMetaData;
import com.raynigon.raylight.service.ShowService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/show")
public class ShowController {

    private final ShowService showService;

    @PostMapping("/cuelist/{cuelist}/play")
    public void playCuelist(@PathVariable("cuelist") int cuelist) {
        showService.playCuelist(cuelist);
    }

    @PostMapping("/cuelist/{cuelist}/stop")
    public void stopCuelist(@PathVariable("cuelist") int cuelist) {
        showService.stopCuelist(cuelist);
    }

    @PostMapping("/cuelist/{cuelist}/goto/{cue}")
    public void goToCue(@PathVariable("cuelist") int cuelist, @PathVariable("cue") int cue) {
        showService.goToCue(cuelist, cue);
    }

}

package com.raynigon.raylight.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.raynigon.raylight.model.ArtNetDMXOutput;
import com.raynigon.raylight.model.CueList;
import com.raynigon.raylight.model.CueListMode;
import com.raynigon.raylight.repository.CueListRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final DMXUniverseService dmxUniverseService;

    private final CueListRepository cueListRepository;

    private Map<Integer, CueList> cueLists;

    @PostConstruct
    public void init() {
        cueLists = cueListRepository.findAll()
                .stream()
                .map(meta->new CueList(meta, dmxUniverseService))
                .collect(Collectors.toMap(CueList::getId, Function.identity()));
    }

    public void playCuelist(int cuelist) {
        if(!cueLists.containsKey(cuelist)){
            throw new RuntimeException("CueList "+cuelist+" was not found");
        }
        CueList cueList = cueLists.get(cuelist);
        cueList.play();
    }

    public void stopCuelist(int cuelist) {
        if(!cueLists.containsKey(cuelist)){
            throw new RuntimeException("CueList "+cuelist+" was not found");
        }
        CueList cueList = cueLists.get(cuelist);
        cueList.stop();
    }

    public void goToCue(int cuelist, int cue) {
        if(!cueLists.containsKey(cuelist)){
            throw new RuntimeException("CueList "+cuelist+" was not found");
        }
        CueList cueList = cueLists.get(cuelist);
        cueList.goToCue(cue);
    }

    @Scheduled(fixedRate = 20)
    public void handleUpdate() {
        cueLists.values().stream()
                .filter(cuelist->cuelist.isActive() && cuelist.getMode() != CueListMode.DEFAULT)
                .forEach(CueList::update);
    }
}

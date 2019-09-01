package com.raynigon.raylight.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.raynigon.raylight.model.DMXUniverse;
import com.raynigon.raylight.model.UniverseMetaData;
import com.raynigon.raylight.repository.UniverseMetaDataRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DMXUniverseService {

    private static Map<Integer, DMXUniverse> universes = new HashMap<>();

    private final UniverseMetaDataRepository repository;

    public DMXUniverse getUniverse(int id) {
        UniverseMetaData info = repository.findById(id).orElse(null);
        if (info == null) {
            return null;
        }
        if (universes.containsKey(info.getId())) {
            return universes.get(info.getId());
        }
        universes.put(info.getId(), new DMXUniverse(info));
        return universes.get(info.getId());
    }

    public List<DMXUniverse> getAllForOutput(String name) {
        return repository.findByOutput(name)
                .stream()
                .map(meta -> getUniverse(meta.getId()))
                .collect(Collectors.toList());
    }
}

package com.raynigon.raylight.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.raynigon.raylight.service.DMXUniverseService;

import lombok.extern.slf4j.Slf4j;

public class CueList {

    private CueListMetaData meta;

    private DMXUniverseService universeService;

    private Map<Integer, Cue> cuesById;

    private List<Cue> cues;

    private Cue active;

    private BPMHandler bpmHandler = new BPMHandler();

    private long lastChange;

    private long intervall;

    public CueList(CueListMetaData meta, DMXUniverseService universeService) {
        this.meta = meta;
        this.universeService = universeService;
        this.cues = meta.getCues()
                .stream()
                .map(cueMeta -> new Cue(cueMeta, universeService))
                .collect(Collectors.toList());
        this.cuesById = cues.stream()
                .collect(Collectors.toMap(cue -> cue.getId(), Function.identity()));
    }

    public int getId() {
        return meta.getId();
    }

    public CueListMode getMode() {
        return meta.getMode();
    }

    public boolean isActive() {
        return active != null;
    }

    public synchronized void play() {
        int index = cues.indexOf(active);
        index = (index + 1) % cues.size();
        this.update(cues.get(index));
        if (getMode() != CueListMode.DEFAULT) {
            bpmHandler.measure();
            intervall = bpmHandler.getInterval();
        }
        lastChange = System.currentTimeMillis() + 5;
    }

    public synchronized void stop() {
        this.update(null);
    }

    public synchronized void goToCue(int cue) {
        this.update(cuesById.get(cue));
    }

    public synchronized void update() {
        if (intervall <= 0) {
            return;
        }
        long nextStart = (lastChange + intervall);
        if (nextStart >= System.currentTimeMillis()) {
            return;
        }
        switch (getMode()) {
            case CHASER_FORWARD:
                next();
                break;
            case CHASER_BACKWARD:
                back();
                break;
        }
        lastChange = System.currentTimeMillis();
    }

    private void next() {
        int index = cues.indexOf(active);
        index = (index + 1) % cues.size();
        this.update(cues.get(index));
    }

    private void back() {
        int index = cues.indexOf(active);
        index = (index < 1) ? (cues.size() - 1) : (index - 1);
        this.update(cues.get(index));
    }

    private void update(Cue newCue) {
        if (active != null) {
            active.stop();
        }
        if (newCue != null) {
            newCue.play();
        }
        active = newCue;
    }

    @Slf4j
    private static class BPMHandler {

        private long[] data = new long[10];
        private int activeIndex = 0;
        private int activeSize = 0;

        public void measure() {
            data[activeIndex] = System.currentTimeMillis();
            activeIndex = (activeIndex + 1) % data.length;
            activeSize = Math.max(activeIndex, activeSize);
        }

        public long getInterval() {
            long diff = 0;
            for (int i = activeIndex; i < (activeIndex + activeSize - 1); i++) {
                diff += (data[(i + 1) % activeSize] - data[i % activeSize]);
            }
            long interval = diff / activeSize;
            log.info("Current Interval {}", interval);
            return interval;
        }
    }
}

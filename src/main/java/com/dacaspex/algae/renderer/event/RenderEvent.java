package com.dacaspex.algae.renderer.event;

import java.util.UUID;

public class RenderEvent {
    private final UUID id;
    private final long time;

    public RenderEvent(UUID id) {
        this.id = id;
        this.time = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public long getTime() {
        return time;
    }
}

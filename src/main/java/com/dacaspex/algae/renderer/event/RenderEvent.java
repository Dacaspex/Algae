package com.dacaspex.algae.renderer.event;

import com.dacaspex.algae.renderer.RenderStage;

import java.util.UUID;

public class RenderEvent {
    private final UUID id;
    private final long time;
    private final RenderStage renderStage;
    private final int progress;

    public RenderEvent(UUID id, RenderStage renderStage, int progress) {
        this.id = id;
        this.time = System.currentTimeMillis();
        this.renderStage = renderStage;
        this.progress = progress;
    }

    public UUID getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public RenderStage getRenderStage() {
        return renderStage;
    }

    public int getProgress() {
        return progress;
    }
}

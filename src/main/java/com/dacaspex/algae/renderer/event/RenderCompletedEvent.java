package com.dacaspex.algae.renderer.event;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class RenderCompletedEvent extends RenderEvent {

    private final BufferedImage image;

    public RenderCompletedEvent(UUID id, BufferedImage image) {
        super(id);

        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}

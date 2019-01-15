package com.dacaspex.algae.gui.display.event;

import com.dacaspex.algae.math.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class ImageDisplayEventDispatcher {

    private List<ImageDisplayEventListener> listeners;

    public ImageDisplayEventDispatcher() {
        this.listeners = new ArrayList<>();
    }

    public void addEventListener(ImageDisplayEventListener listener) {
        listeners.add(listener);
    }

    public void dispatchZoomInEvent(Vector2d point) {
        listeners.forEach(l -> l.onZoomIn(point));
    }

    public void dispatchZoomOutEvent(Vector2d point) {
        listeners.forEach(l -> l.onZoomOut(point));
    }

    public void dispatchTranslateEvent(Vector2d point) {
        listeners.forEach(l -> l.onTranslate(point));
    }
}

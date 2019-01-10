package com.dacaspex.algae.renderer.event;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {

    private List<RendererEventListener> eventListeners;

    public EventDispatcher() {
        this.eventListeners = new ArrayList<>();
    }

    public void addEventListener(RendererEventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public void removeEventListener(RendererEventListener eventListener) {
        eventListeners.remove(eventListener);
    }

    public void dispatchOnRenderStartedEvent(RenderEvent renderEvent) {
        eventListeners.forEach(l -> l.onRenderStarted(renderEvent));
    }

    public void dispatchOnRenderCanceledEvent(RenderEvent event) {
        eventListeners.forEach(l -> l.onRenderCanceled(event));
    }

    public void dispatchOnPreProcessingCompleted(RenderEvent event) {
        eventListeners.forEach(l -> l.onPreProcessingCompleted(event));
    }

    public void dispatchOnRenderCompleted(RenderCompletedEvent event) {
        eventListeners.forEach(l -> l.onRenderCompleted(event));
    }
}

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

    public void dispatchOnRenderStartedEvent(RenderEvent event) {
        eventListeners.forEach(l -> l.onRenderStarted(event));
        eventListeners.forEach(l -> l.onGenericEvent(event));
    }

    public void dispatchOnRenderCanceledEvent(RenderEvent event) {
        eventListeners.forEach(l -> l.onRenderCanceled(event));
        eventListeners.forEach(l -> l.onGenericEvent(event));
    }

    public void dispatchOnPreProcessingCompleted(RenderEvent event) {
        eventListeners.forEach(l -> l.onPreProcessingCompleted(event));
        eventListeners.forEach(l -> l.onGenericEvent(event));
    }

    public void dispatchOnRenderCompleted(RenderCompletedEvent event) {
        eventListeners.forEach(l -> l.onRenderCompleted(event));
        eventListeners.forEach(l -> l.onGenericEvent(event));
    }

    public void dispatchOnProgressEvent(RenderEvent event) {
        eventListeners.forEach(l -> l.onProgress(event));
        eventListeners.forEach(l -> l.onGenericEvent(event));
    }
}

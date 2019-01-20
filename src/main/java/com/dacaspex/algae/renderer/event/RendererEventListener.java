package com.dacaspex.algae.renderer.event;

public interface RendererEventListener {

    public void onRenderStarted(RenderEvent event);

    public void onRenderCanceled(RenderEvent event);

    public void onPreProcessingCompleted(RenderEvent event);

    public void onRenderCompleted(RenderCompletedEvent event);

    public void onProgress(RenderEvent event);

    public void onGenericEvent(RenderEvent event);
}

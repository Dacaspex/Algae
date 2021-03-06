package com.dacaspex.algae.renderer;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.util.math.Scale;
import com.dacaspex.algae.renderer.event.EventDispatcher;
import com.dacaspex.algae.renderer.event.RendererEventListener;

public class Renderer {

    private EventDispatcher eventDispatcher;
    private RenderProcess activeProcess;

    public Renderer() {
        this.eventDispatcher = new EventDispatcher();
    }

    /**
     * Stops the previous render (or queue) and asynchronously schedules a new render. Note that the
     * render may not have started after this method concluded.
     *
     * @param fractal        Fractal
     * @param colorScheme    Color scheme
     * @param scale          Scale
     * @param renderSettings Render settings
     */
    public void render(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderSettings) {
        if (activeProcess != null) {
            activeProcess.interrupt();
        }

        activeProcess = new RenderProcess(fractal, colorScheme, scale, renderSettings, 2, eventDispatcher);
        activeProcess.start();
    }

    /**
     * Cancels the currently active render asynchronously. Note that when this method returns, the render
     * may not have been canceled yet. Use the event listener to get the exact time when the render is
     * canceled.
     */
    public void cancel() {
        if (activeProcess != null) {
            activeProcess.interrupt();
        }
    }

    public void addEventListener(RendererEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }
}

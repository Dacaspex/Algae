package com.dacaspex.algae.renderer;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;
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

        activeProcess = new RenderProcess(fractal, colorScheme, scale, renderSettings, 16, eventDispatcher);
        activeProcess.start();
    }

    public void addEventListener(RendererEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }

    public void removeEventListener(RendererEventListener eventListener) {
        eventDispatcher.removeEventListener(eventListener);
    }
}

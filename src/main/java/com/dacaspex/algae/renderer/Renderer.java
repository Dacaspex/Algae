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
        // 1. Cancel current job (if present)
        //      Fire render canceled job
        // 2. Create job information
        // 3. Start pre-processing
        //      Fire render started event
        // 4. Start render
        //      Fire pre-processing completed event
        // 5. Render completed
        // 6. Start stitching
        // 7. Stitch completed
        //      Fire render completed event

        if (activeProcess != null) {
            activeProcess.interrupt();
        }

        activeProcess = new RenderProcess(fractal, colorScheme, scale, renderSettings, 1, eventDispatcher);
        activeProcess.start();
    }

    public void addEventListener(RendererEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }

    public void removeEventListener(RendererEventListener eventListener) {
        eventDispatcher.removeEventListener(eventListener);
    }
}

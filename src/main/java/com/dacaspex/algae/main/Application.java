package com.dacaspex.algae.main;

import com.dacaspex.algae.render.Renderer;

public class Application {

    private static Application app;

    private Renderer renderer;
    private Renderer exportRenderer;

    private Application() {
        this.renderer = new Renderer();
        this.exportRenderer = new Renderer();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Renderer getExportRenderer() {
        return exportRenderer;
    }

    public void close() {
        System.exit(0);
    }

    public static Application get() {
        if (app == null) {
            app = new Application();
        }

        return app;
    }
}

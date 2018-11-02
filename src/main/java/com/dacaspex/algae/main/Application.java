package com.dacaspex.algae.main;

import com.dacaspex.algae.render.Renderer;

public class Application {

    private static Application app;

    private Renderer renderer;

    private Application() {
        this.renderer = new Renderer();
    }

    public Renderer getRenderer() {
        return renderer;
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

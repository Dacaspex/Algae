package com.dacaspex.algae.render;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.render.settings.RenderSettings;

public class Renderer {

    private RenderCompleteListener listener;

    public void render(Fractal fractal, ColorScheme colorScheme, RenderSettings options) {

        if (colorScheme.getPreProcessor() != null) {

        }

        // TODO: Pre-processing
        // TODO: Rendering
    }

    public void setListener(RenderCompleteListener listener) {
        this.listener = listener;
    }
}

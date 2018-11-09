package com.dacaspex.algae.render;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.render.settings.RenderSettings;

public class RenderRequest {
    Fractal fractal;
    ColorScheme colorScheme;
    Scale scale;
    RenderSettings renderSettings;

    RenderRequest(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderSettings) {
        this.fractal = fractal;
        this.colorScheme = colorScheme;
        this.scale = scale;
        this.renderSettings = renderSettings;
    }
}

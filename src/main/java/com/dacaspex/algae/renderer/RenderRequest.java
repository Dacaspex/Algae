package com.dacaspex.algae.renderer;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;

public class RenderRequest {
    public final Fractal fractal;
    public final ColorScheme colorScheme;
    public final Scale scale;
    public final RenderSettings renderSettings;

    public RenderRequest(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderSettings) {
        this.fractal = fractal;
        this.colorScheme = colorScheme;
        this.scale = scale;
        this.renderSettings = renderSettings;
    }
}

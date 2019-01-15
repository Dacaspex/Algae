package com.dacaspex.algae.main;

import com.dacaspex.algae.gui.Gui;
import com.dacaspex.algae.gui.settings.colorScheme.*;
import com.dacaspex.algae.gui.settings.fractal.BurningShipSettingsProvider;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;
import com.dacaspex.algae.gui.settings.fractal.JuliaSettingsProvider;
import com.dacaspex.algae.gui.settings.fractal.MandelbrotSettingsProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public class Launcher {

    public static void main(String[] args) {

        // We use a {@code LinkedHashMap} for this implementation to make sure the order is the same
        // everywhere as the insertion order.
        Map<String, FractalSettingsProvider> fractalSettings = new LinkedHashMap<String, FractalSettingsProvider>() {{
            put("Julia Fractal", new JuliaSettingsProvider());
            put("Mandelbrot Fractal", new MandelbrotSettingsProvider());
            put("Burning Ship Fractal", new BurningShipSettingsProvider());
        }};
        Map<String, ColorSchemeSettingsProvider> colorSchemeSettings = new LinkedHashMap<String, ColorSchemeSettingsProvider>() {{
            put("Grayscale", new GrayscaleSettingsProvider());
            put("Normalised Grayscale", new NormalisedGrayscaleSettingsProvider());
            put("Sin Waves", new WaveColorSchemeSettingsProvider());
            put("Angle Grayscale", new AngleGrayscaleSettingsProvider());
        }};

        (new Gui(fractalSettings, colorSchemeSettings)).build();
    }
}

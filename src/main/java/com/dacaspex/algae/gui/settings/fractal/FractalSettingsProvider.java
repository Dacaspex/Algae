package com.dacaspex.algae.gui.settings.fractal;

import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.gui.settings.JsonExportable;
import com.dacaspex.algae.gui.settings.JsonImportable;
import com.dacaspex.algae.gui.settings.SettingsProvider;

public interface FractalSettingsProvider extends SettingsProvider, JsonExportable, JsonImportable {

    public Fractal getFractal();
}

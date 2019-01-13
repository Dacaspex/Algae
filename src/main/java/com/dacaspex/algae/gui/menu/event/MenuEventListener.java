package com.dacaspex.algae.gui.menu.event;

import com.dacaspex.algae.legacyGui.settings.colorScheme.ColorSchemeSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.FractalSettings;

public interface MenuEventListener {

    public void onFractalSettingsSelected(FractalSettings fractalSettings);

    public void onColorSchemeSettingsSelected(ColorSchemeSettings colorSchemeSettings);
}

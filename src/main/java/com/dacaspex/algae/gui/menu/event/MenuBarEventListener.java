package com.dacaspex.algae.gui.menu.event;

import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettingsProvider;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;

public interface MenuBarEventListener {

    public void onFractalSettingsSelected(FractalSettingsProvider fractalSettings);

    public void onColorSchemeSettingsSelected(ColorSchemeSettingsProvider colorSchemeSettings);

    public void onFractalSettingsOpened();

    public void onColorSchemeSettingsOpened();

    public void onExportDisplayOpened();
}

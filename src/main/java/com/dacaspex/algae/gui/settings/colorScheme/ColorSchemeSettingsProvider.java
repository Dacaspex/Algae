package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.gui.settings.SettingsProvider;

public interface ColorSchemeSettingsProvider extends SettingsProvider {

    public ColorScheme getColorScheme();
}

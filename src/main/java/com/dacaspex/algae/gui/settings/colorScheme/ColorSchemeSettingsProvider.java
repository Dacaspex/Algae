package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.gui.settings.JsonExportable;
import com.dacaspex.algae.gui.settings.JsonImportable;
import com.dacaspex.algae.gui.settings.SettingsProvider;

public interface ColorSchemeSettingsProvider extends SettingsProvider, JsonExportable, JsonImportable {

    public ColorScheme getColorScheme();
}

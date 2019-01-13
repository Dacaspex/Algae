package com.dacaspex.algae.legacyGui.menu.item;

import com.dacaspex.algae.legacyGui.Display;
import com.dacaspex.algae.legacyGui.settings.colorScheme.ColorSchemeSettings;

import javax.swing.JMenuItem;

public class ColorSchemeSettingsMenuItem extends JMenuItem {

    public ColorSchemeSettingsMenuItem(
            String name,
            ColorSchemeSettings settings,
            Display display
    ) {
        setText(name);
        addActionListener(l -> {
            display.getColorSchemeSettingsDisplay().setSettings(settings);
            display.render();
        });
    }
}

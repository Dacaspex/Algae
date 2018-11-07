package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.Display;
import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettings;

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

package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuEventDispatcher;
import com.dacaspex.algae.legacyGui.settings.colorScheme.ColorSchemeSettings;

import javax.swing.*;

public class SelectColorSchemeMenuItem extends JMenuItem {

    public SelectColorSchemeMenuItem(
            MenuEventDispatcher eventDispatcher,
            String name,
            ColorSchemeSettings colorSchemeSettings
    ) {
        setText(name);
        addActionListener(l -> {
            eventDispatcher.dispatchColorSchemeSettingsSelected(colorSchemeSettings);
        });
    }
}

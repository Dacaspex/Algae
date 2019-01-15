package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;
import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettingsProvider;

import javax.swing.*;

public class SelectColorSchemeMenuItem extends JMenuItem {

    public SelectColorSchemeMenuItem(
            MenuBarEventDispatcher eventDispatcher,
            String name,
            ColorSchemeSettingsProvider colorSchemeSettingsProvider
    ) {
        setText(name);
        addActionListener(l -> {
            eventDispatcher.dispatchColorSchemeSettingsSelected(colorSchemeSettingsProvider);
        });
    }
}

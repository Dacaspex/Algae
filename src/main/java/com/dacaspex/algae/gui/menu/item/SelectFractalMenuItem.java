package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;

import javax.swing.*;

public class SelectFractalMenuItem extends JMenuItem {

    public SelectFractalMenuItem(
            MenuBarEventDispatcher eventDispatcher,
            String name,
            FractalSettingsProvider settingsProvider
    ) {
        setText(name);
        addActionListener(l -> {
            eventDispatcher.dispatchFractalSettingsSelected(settingsProvider);
        });
    }
}

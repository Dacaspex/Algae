package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuEventDispatcher;
import com.dacaspex.algae.legacyGui.settings.fractal.FractalSettings;

import javax.swing.*;

public class SelectFractalMenuItem extends JMenuItem {

    public SelectFractalMenuItem(
            MenuEventDispatcher eventDispatcher,
            String name,
            FractalSettings settings
    ) {
        setText(name);
        addActionListener(l -> {
            eventDispatcher.dispatchFractalSettingsSelected(settings);
        });
    }
}

package com.dacaspex.algae.legacyGui.menu.item;

import com.dacaspex.algae.legacyGui.Display;
import com.dacaspex.algae.legacyGui.settings.fractal.FractalSettings;

import javax.swing.JMenuItem;

public class FractalSettingsMenuItem extends JMenuItem {

    public FractalSettingsMenuItem(String name, FractalSettings settings, Display display) {
        setText(name);
        addActionListener(l -> {
            display.getFractalSettingsDisplay().setSettings(settings);
            display.render();
        });
    }
}

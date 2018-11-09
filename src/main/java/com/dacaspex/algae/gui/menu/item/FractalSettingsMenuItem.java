package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.Display;
import com.dacaspex.algae.gui.settings.fractal.FractalSettings;

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

package com.dacaspex.algae.legacyGui.menu.item;

import com.dacaspex.algae.legacyGui.Display;

import javax.swing.JMenuItem;

public class OpenFractalSettingsMenuItem extends JMenuItem {

    public OpenFractalSettingsMenuItem(Display display) {
        setText("Open fractal settings");
        addActionListener(e -> {
            display.openFractalSettings();
        });
    }
}

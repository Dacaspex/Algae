package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.Display;

import javax.swing.JMenuItem;

public class OpenFractalSettingsMenuItem extends JMenuItem {

    public OpenFractalSettingsMenuItem(Display display) {
        setText("Open fractal settings");
        addActionListener(e -> {
            display.openFractalSettings();
        });
    }
}

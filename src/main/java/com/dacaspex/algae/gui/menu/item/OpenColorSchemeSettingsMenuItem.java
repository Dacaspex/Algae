package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.Display;

import javax.swing.JMenuItem;

public class OpenColorSchemeSettingsMenuItem extends JMenuItem {

    public OpenColorSchemeSettingsMenuItem(Display display) {
        setText("Open color scheme settings");
        addActionListener(e -> {
            display.openColorSchemeSettings();
        });
    }
}

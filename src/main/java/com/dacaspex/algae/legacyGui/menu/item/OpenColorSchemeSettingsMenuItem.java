package com.dacaspex.algae.legacyGui.menu.item;

import com.dacaspex.algae.legacyGui.Display;

import javax.swing.JMenuItem;

public class OpenColorSchemeSettingsMenuItem extends JMenuItem {

    public OpenColorSchemeSettingsMenuItem(Display display) {
        setText("Open color scheme settings");
        addActionListener(e -> {
            display.openColorSchemeSettings();
        });
    }
}

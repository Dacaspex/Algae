package com.dacaspex.algae.legacyGui.menu.item;

import com.dacaspex.algae.legacyGui.Display;

import javax.swing.JMenuItem;

public class OpenExportSettingsMenuItem extends JMenuItem {

    public OpenExportSettingsMenuItem(Display display) {
        setText("Export image");
        addActionListener(e -> {
            display.openExportSettings();
        });
    }
}

package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.Display;
import com.dacaspex.algae.gui.settings.ExportSettings;

import javax.swing.JMenuItem;

public class OpenExportSettingsMenuItem extends JMenuItem {

    public OpenExportSettingsMenuItem(Display display) {
        setText("Export image");
        addActionListener(e -> {
            display.openExportSettings();
        });
    }
}

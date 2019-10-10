package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class OpenExportFractalSettingsDisplayMenuItem extends JMenuItem {
    public OpenExportFractalSettingsDisplayMenuItem(MenuBarEventDispatcher eventDispatcher, String name) {
        setText(name);
        addActionListener(l -> eventDispatcher.dispatchExportFractalSettingsDisplayOpened());
    }
}

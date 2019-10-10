package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class OpenImportFractalSettingsDisplayMenuItem extends JMenuItem {
    public OpenImportFractalSettingsDisplayMenuItem(MenuBarEventDispatcher eventDispatcher, String name) {
        setText(name);
        addActionListener(l -> eventDispatcher.dispatchImportFractalSettingsDisplayOpened());
    }
}

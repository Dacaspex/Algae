package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class OpenImportColorSchemeSettingsDisplayMenuItem extends JMenuItem {
    public OpenImportColorSchemeSettingsDisplayMenuItem(MenuBarEventDispatcher dispatcher, String name) {
        setText(name);
        addActionListener(l -> dispatcher.dispatchImportColorSchemeSettingsDisplayOpened());
    }
}

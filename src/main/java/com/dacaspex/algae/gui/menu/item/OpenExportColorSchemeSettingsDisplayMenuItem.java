package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class OpenExportColorSchemeSettingsDisplayMenuItem extends JMenuItem {
    public OpenExportColorSchemeSettingsDisplayMenuItem(MenuBarEventDispatcher dispatcher, String name) {
        setText(name);
        addActionListener(l -> dispatcher.dispatchExportColorSchemeSettingsDisplayOpened());
    }
}

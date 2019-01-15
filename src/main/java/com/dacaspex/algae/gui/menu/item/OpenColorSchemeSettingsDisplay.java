package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class OpenColorSchemeSettingsDisplay extends JMenuItem {

    public OpenColorSchemeSettingsDisplay(MenuBarEventDispatcher eventDispatcher, String name) {
        setText(name);
        addActionListener(l -> eventDispatcher.dispatchColorSchemeSettingsDisplayOpened());
    }
}

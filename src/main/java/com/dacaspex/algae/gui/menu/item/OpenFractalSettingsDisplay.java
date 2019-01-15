package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class OpenFractalSettingsDisplay extends JMenuItem {

    public OpenFractalSettingsDisplay(MenuBarEventDispatcher eventDispatcher, String name) {
        setText(name);
        addActionListener(l -> eventDispatcher.dispatchFractalSettingsDisplayOpened());
    }
}

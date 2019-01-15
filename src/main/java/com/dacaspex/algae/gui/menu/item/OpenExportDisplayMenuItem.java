package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class OpenExportDisplayMenuItem extends JMenuItem {

    public OpenExportDisplayMenuItem(MenuBarEventDispatcher eventDispatcher, String name) {
        setText(name);
        addActionListener(l -> eventDispatcher.dispatchExportDisplayOpened());
    }
}

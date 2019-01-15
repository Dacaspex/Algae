package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;

import javax.swing.*;

public class ExitMenuItem extends JMenuItem {

    public ExitMenuItem(MenuBarEventDispatcher eventDispatcher, String name) {
        setText(name);
        addActionListener(l -> eventDispatcher.dispatchExit());
    }
}

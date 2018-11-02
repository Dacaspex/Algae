package com.dacaspex.algae.gui.menu.item;

import com.dacaspex.algae.main.Application;

import javax.swing.JMenuItem;

public class ExitMenuItem extends JMenuItem {

    public ExitMenuItem() {
        setText("Exit");
        addActionListener(e -> {
            Application.get().close();
        });
    }
}

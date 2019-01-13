package com.dacaspex.algae.legacyGui.menu.item;

import javax.swing.*;

public class ExitMenuItem extends JMenuItem {

    public ExitMenuItem() {
        setText("Exit");
        addActionListener(e -> System.exit(0));
    }
}

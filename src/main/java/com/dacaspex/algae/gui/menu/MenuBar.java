package com.dacaspex.algae.gui.menu;

import com.dacaspex.algae.gui.Display;
import com.dacaspex.algae.gui.menu.item.ExitMenuItem;
import com.dacaspex.algae.gui.menu.item.OpenColorSchemeSettingsMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

    private Display display;

    public MenuBar(Display display) {
        this.display = display;

        build();
    }

    private void build() {
        // Build file menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new ExitMenuItem());

        // Build fractal menu
        JMenu fractalMenu = new JMenu("Fractals");

        // Build color scheme menu
        JMenu colorSchemeMenu = new JMenu("Color schemes");
        colorSchemeMenu.add(new OpenColorSchemeSettingsMenuItem(display));

        add(fileMenu);
        add(fractalMenu);
        add(colorSchemeMenu);
    }
}

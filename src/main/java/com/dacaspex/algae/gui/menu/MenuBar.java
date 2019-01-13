package com.dacaspex.algae.gui.menu;

import com.dacaspex.algae.gui.menu.event.MenuEventDispatcher;
import com.dacaspex.algae.gui.menu.event.MenuEventListener;
import com.dacaspex.algae.gui.menu.item.SelectColorSchemeMenuItem;
import com.dacaspex.algae.gui.menu.item.SelectFractalMenuItem;
import com.dacaspex.algae.legacyGui.menu.item.ExitMenuItem;
import com.dacaspex.algae.legacyGui.settings.colorScheme.AngleGrayscaleSettings;
import com.dacaspex.algae.legacyGui.settings.colorScheme.GrayscaleSettings;
import com.dacaspex.algae.legacyGui.settings.colorScheme.NormalisedGrayscaleSettings;
import com.dacaspex.algae.legacyGui.settings.colorScheme.WaveColorSchemeSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.BurningShipSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.JuliaSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.MandelbrotSettings;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private final MenuEventDispatcher eventDispatcher;

    public MenuBar() {
        this.eventDispatcher = new MenuEventDispatcher();
    }

    public void build() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new ExitMenuItem());
        // TODO: Export menu

        JMenu fractalMenu = new JMenu("Fractals");
        // TODO: Open fractal settings
        fractalMenu.addSeparator();
        fractalMenu.add(new SelectFractalMenuItem(
                eventDispatcher,
                "Julia fractal",
                new JuliaSettings()
        ));
        fractalMenu.add(new SelectFractalMenuItem(
                eventDispatcher,
                "Mandelbrot fractal",
                new MandelbrotSettings()
        ));
        fractalMenu.add(new SelectFractalMenuItem(
                eventDispatcher,
                "Burning Ship fractal",
                new BurningShipSettings()
        ));

        // Build color scheme menu
        JMenu colorSchemeMenu = new JMenu("Color schemes");
        // TODO: Open color scheme menu
        colorSchemeMenu.addSeparator();
        colorSchemeMenu.add(new SelectColorSchemeMenuItem(
                eventDispatcher,
                "Grayscale",
                new GrayscaleSettings()
        ));
        colorSchemeMenu.add(new SelectColorSchemeMenuItem(
                eventDispatcher,
                "Normalised grayscale",
                new NormalisedGrayscaleSettings()
        ));
        colorSchemeMenu.add(new SelectColorSchemeMenuItem(
                eventDispatcher,
                "Trigonometric",
                new WaveColorSchemeSettings()
        ));
        colorSchemeMenu.add(new SelectColorSchemeMenuItem(
                eventDispatcher,
                "Angle grayscale",
                new AngleGrayscaleSettings()
        ));

        add(fileMenu);
        add(fractalMenu);
        add(colorSchemeMenu);
    }

    public void addEventListener(MenuEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }
}

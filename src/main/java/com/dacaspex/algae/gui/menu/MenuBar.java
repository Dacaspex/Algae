package com.dacaspex.algae.gui.menu;

import com.dacaspex.algae.gui.Display;
import com.dacaspex.algae.gui.menu.item.*;
import com.dacaspex.algae.gui.settings.colorScheme.AngleGrayscaleSettings;
import com.dacaspex.algae.gui.settings.colorScheme.GrayscaleSettings;
import com.dacaspex.algae.gui.settings.colorScheme.NormalisedGrayscaleSettings;
import com.dacaspex.algae.gui.settings.colorScheme.WaveColorSchemeSettings;
import com.dacaspex.algae.gui.settings.fractal.BurningShipSettings;
import com.dacaspex.algae.gui.settings.fractal.JuliaSettings;
import com.dacaspex.algae.gui.settings.fractal.MandelbrotSettings;

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
        fileMenu.add(new OpenExportSettingsMenuItem(display));

        // Build fractal menu
        JMenu fractalMenu = new JMenu("Fractals");
        fractalMenu.add(new OpenFractalSettingsMenuItem(display));
        fractalMenu.addSeparator();
        fractalMenu.add(new FractalSettingsMenuItem(
                "Julia set",
                new JuliaSettings(),
                display
        ));
        fractalMenu.add(new FractalSettingsMenuItem(
                "Mandelbrot",
                new MandelbrotSettings(),
                display
        ));
        fractalMenu.add(new FractalSettingsMenuItem(
                "Burning ship",
                new BurningShipSettings(),
                display
        ));

        // Build color scheme menu
        JMenu colorSchemeMenu = new JMenu("Color schemes");
        colorSchemeMenu.add(new OpenColorSchemeSettingsMenuItem(display));
        colorSchemeMenu.addSeparator();
        colorSchemeMenu.add(new ColorSchemeSettingsMenuItem(
                "Grayscale",
                new GrayscaleSettings(),
                display
        ));
        colorSchemeMenu.add(new ColorSchemeSettingsMenuItem(
                "Normalised grayscale",
                new NormalisedGrayscaleSettings(),
                display
        ));
        colorSchemeMenu.add(new ColorSchemeSettingsMenuItem(
                "Trigonometric",
                new WaveColorSchemeSettings(),
                display
        ));
        colorSchemeMenu.add(new ColorSchemeSettingsMenuItem(
                "Angle grayscale",
                new AngleGrayscaleSettings(),
                display
        ));

        add(fileMenu);
        add(fractalMenu);
        add(colorSchemeMenu);
    }
}

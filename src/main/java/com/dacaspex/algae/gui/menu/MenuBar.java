package com.dacaspex.algae.gui.menu;

import com.dacaspex.algae.gui.menu.event.MenuBarEventDispatcher;
import com.dacaspex.algae.gui.menu.event.MenuBarEventListener;
import com.dacaspex.algae.gui.menu.item.*;
import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettingsProvider;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;

import javax.swing.*;
import java.util.Map;

public class MenuBar extends JMenuBar {

    private final MenuBarEventDispatcher eventDispatcher;
    private final Map<String, FractalSettingsProvider> fractalSettings;
    private final Map<String, ColorSchemeSettingsProvider> colorSchemeSettings;

    public MenuBar(
            Map<String, FractalSettingsProvider> fractalSettings,
            Map<String, ColorSchemeSettingsProvider> colorSchemeSettings
    ) {
        this.eventDispatcher = new MenuBarEventDispatcher();
        this.fractalSettings = fractalSettings;
        this.colorSchemeSettings = colorSchemeSettings;
    }

    public void build() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new OpenExportDisplayMenuItem(eventDispatcher, "Export image"));
        fileMenu.add(new OpenExportFractalSettingsDisplayMenuItem(eventDispatcher, "Export fractal settings"));
        fileMenu.add(new OpenExportColorSchemeSettingsDisplayMenuItem(eventDispatcher, "Export color scheme settings"));
        fileMenu.addSeparator();
        fileMenu.add(new OpenImportFractalSettingsDisplayMenuItem(eventDispatcher, "Import fractal settings"));
        fileMenu.add(new OpenImportColorSchemeSettingsDisplayMenuItem(eventDispatcher, "Import color scheme settings"));
        fileMenu.addSeparator();
        fileMenu.add(new ExitMenuItem(eventDispatcher, "Close application"));

        JMenu fractalMenu = new JMenu("Fractals");
        fractalMenu.add(new OpenFractalSettingsDisplay(eventDispatcher, "Open fractal settings"));
        fractalMenu.addSeparator();
        fractalSettings.forEach(
                (key, value) -> fractalMenu.add(new SelectFractalMenuItem(eventDispatcher, key, value))
        );

        // Build color scheme menu
        JMenu colorSchemeMenu = new JMenu("Color schemes");
        colorSchemeMenu.add(new OpenColorSchemeSettingsDisplay(eventDispatcher, "Open color scheme settings"));
        colorSchemeMenu.addSeparator();
        colorSchemeSettings.forEach(
                (key, value) -> colorSchemeMenu.add(new SelectColorSchemeMenuItem(eventDispatcher, key, value))
        );

        add(fileMenu);
        add(fractalMenu);
        add(colorSchemeMenu);
    }

    public void addEventListener(MenuBarEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }
}

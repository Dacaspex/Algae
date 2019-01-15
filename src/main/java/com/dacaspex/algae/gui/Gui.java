package com.dacaspex.algae.gui;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.gui.display.ImageDisplay;
import com.dacaspex.algae.gui.menu.MenuBar;
import com.dacaspex.algae.gui.settings.SettingsDisplay;
import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettingsProvider;
import com.dacaspex.algae.gui.settings.event.SettingUpdatedListener;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.SortedMap;

public class Gui extends JFrame {

    private final int width;
    private final int height;

    private final Map<String, FractalSettingsProvider> fractalSettings;
    private final Map<String, ColorSchemeSettingsProvider> colorSchemeSettings;

    private final Renderer renderer;
    private final MenuBar menuBar;
    private final ImageDisplay imageDisplay;
    private final StatusBar statusBar;

    private final SettingsDisplay fractalSettingsDisplay;
    private final SettingsDisplay colorSchemeSettingsDisplay;

    private Fractal fractal;
    private ColorScheme colorScheme;
    private Scale scale;
    private RenderSettings renderSettings;

    public Gui(
            Map<String, FractalSettingsProvider> fractalSettings,
            Map<String, ColorSchemeSettingsProvider> colorSchemeSettings
    ) {
        this.fractalSettings = fractalSettings;
        this.colorSchemeSettings = colorSchemeSettings;

        this.width = 800;
        this.height = 800;

        String defaultFractalKey = fractalSettings.keySet().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provide at least one fractal settings provider"));
        String defaultColorSchemeKey = colorSchemeSettings.keySet().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provide at least one color scheme settings provider"));

        this.fractalSettingsDisplay = new SettingsDisplay(fractalSettings.get(defaultFractalKey));
        this.colorSchemeSettingsDisplay = new SettingsDisplay(colorSchemeSettings.get(defaultColorSchemeKey));

        this.fractal = fractalSettings.get(defaultFractalKey).getFractal();
        this.colorScheme = colorSchemeSettings.get(defaultColorSchemeKey).getColorScheme();
        this.scale = new Scale();
        // Render settings are intentionally not initialised now, since it requires the dimensions of the
        // image display.

        this.renderer = new Renderer();
        this.menuBar = new MenuBar(fractalSettings, colorSchemeSettings);
        this.imageDisplay = new ImageDisplay();
        this.statusBar = new StatusBar();
    }

    public void build() {
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Renderer
        renderer.addEventListener(new RendererEventListener());

        // Build settings menus
        fractalSettingsDisplay.build();
        fractalSettingsDisplay.addEventListener(new FractalSettingUpdatedListener());
        colorSchemeSettingsDisplay.build();
        colorSchemeSettingsDisplay.addEventListener(new ColorSchemeSettingUpdatedListener());

        // Build menu bar
        menuBar.build();
        menuBar.addEventListener(new MenuBarBarEventListener());
        setJMenuBar(menuBar);

        // Build image display
        imageDisplay.build();
        add(imageDisplay, BorderLayout.CENTER);

        // Build status bar
        statusBar.build();
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
        pack();

        renderSettings = new RenderSettings(imageDisplay.getWidth(), imageDisplay.getHeight());
        render();
    }

    private void render() {
        renderer.render(fractal, colorScheme, scale, renderSettings);
    }

    private class MenuBarBarEventListener implements com.dacaspex.algae.gui.menu.event.MenuBarEventListener {
        @Override
        public void onFractalSettingsSelected(FractalSettingsProvider fractalSettings) {

        }

        @Override
        public void onColorSchemeSettingsSelected(ColorSchemeSettingsProvider colorSchemeSettings) {

        }

        @Override
        public void onFractalSettingsOpened() {

        }

        @Override
        public void onColorSchemeSettingsOpened() {

        }
    }

    private class RendererEventListener extends RendererEventAdapter {
        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {
            imageDisplay.updateImage(event.getImage());
        }
    }

    private class FractalSettingUpdatedListener implements SettingUpdatedListener {
        @Override
        public void onSettingUpdated(Property property) {

        }
    }

    private class ColorSchemeSettingUpdatedListener implements SettingUpdatedListener {
        @Override
        public void onSettingUpdated(Property property) {

        }
    }
}

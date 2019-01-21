package com.dacaspex.algae.gui;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.gui.display.ImageDisplay;
import com.dacaspex.algae.gui.export.ExportDisplay;
import com.dacaspex.algae.gui.menu.MenuBar;
import com.dacaspex.algae.gui.settings.SettingsDisplay;
import com.dacaspex.algae.gui.settings.SettingsProvider;
import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettingsProvider;
import com.dacaspex.algae.gui.settings.event.SettingUpdatedListener;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;
import com.dacaspex.algae.gui.status.StatusBar;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RenderEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Gui extends JFrame {

    private final int width;
    private final int height;

    private final Renderer renderer;
    private final MenuBar menuBar;
    private final ImageDisplay imageDisplay;
    private final StatusBar statusBar;

    private final SettingsDisplay fractalSettingsDisplay;
    private final SettingsDisplay colorSchemeSettingsDisplay;

    private final ExportDisplay exportDisplay;

    private boolean exited;

    private Fractal fractal;
    private ColorScheme colorScheme;
    private Scale scale;
    private RenderSettings renderSettings;

    public Gui(
            Map<String, FractalSettingsProvider> fractalSettings,
            Map<String, ColorSchemeSettingsProvider> colorSchemeSettings
    ) {
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

        this.exportDisplay = new ExportDisplay();

        this.fractal = fractalSettings.get(defaultFractalKey).getFractal();
        this.colorScheme = colorSchemeSettings.get(defaultColorSchemeKey).getColorScheme();
        this.scale = new Scale();
        // Render settings are intentionally not initialised now, since it requires the dimensions of the
        // image display.

        this.exited = false;

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

        // Build export display
        exportDisplay.build();

        // Build menu bar
        menuBar.build();
        menuBar.addEventListener(new MenuBarBarEventListener());
        setJMenuBar(menuBar);

        // Build image display
        imageDisplay.build();
        imageDisplay.addEventListener(new ImageDisplayEventListener());
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
            fractal = fractalSettings.getFractal();
            fractalSettingsDisplay.updateSettingsProvider(fractalSettings);
            render();
        }

        @Override
        public void onColorSchemeSettingsSelected(ColorSchemeSettingsProvider colorSchemeSettings) {
            colorScheme = colorSchemeSettings.getColorScheme();
            colorSchemeSettingsDisplay.updateSettingsProvider(colorSchemeSettings);
            render();
        }

        @Override
        public void onFractalSettingsOpened() {
            fractalSettingsDisplay.open();
        }

        @Override
        public void onColorSchemeSettingsOpened() {
            colorSchemeSettingsDisplay.open();
        }

        @Override
        public void onExportDisplayOpened() {
            exportDisplay.open(fractal, colorScheme, scale);
        }

        @Override
        public void onExit() {
            // Wait for the render to cancel for graceful termination
            exited = true;
            renderer.cancel();
        }
    }

    private class RendererEventListener extends RendererEventAdapter {
        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {
            imageDisplay.updateImage(event.getImage());
        }

        @Override
        public void onRenderCanceled(RenderEvent event) {
            if (exited) {
                System.exit(0);
            }
        }

        @Override
        public void onGenericEvent(RenderEvent event) {
            statusBar.updateProgress(event.getProgress());
            statusBar.updateRenderStage(event.getRenderStage());
        }
    }

    private class FractalSettingUpdatedListener implements SettingUpdatedListener {
        @Override
        public void onSettingUpdated(SettingsProvider settingsProvider) {
            if (settingsProvider instanceof FractalSettingsProvider) {
                fractal = ((FractalSettingsProvider) settingsProvider).getFractal();
            }

            render();
        }
    }

    private class ColorSchemeSettingUpdatedListener implements SettingUpdatedListener {
        @Override
        public void onSettingUpdated(SettingsProvider settingsProvider) {
            if (settingsProvider instanceof ColorSchemeSettingsProvider) {
                colorScheme = ((ColorSchemeSettingsProvider) settingsProvider).getColorScheme();
            }

            render();
        }
    }

    private class ImageDisplayEventListener implements com.dacaspex.algae.gui.display.event.ImageDisplayEventListener {
        @Override
        public void onZoomIn(Vector2d point) {
            Vector2d center = scale.getScreenPointInScale(point, imageDisplay.getWidth(), imageDisplay.getHeight());
            scale = new Scale(center, scale.getZoomLevel() * 2);

            render();
        }

        @Override
        public void onZoomOut(Vector2d point) {
            Vector2d center = scale.getScreenPointInScale(point, imageDisplay.getWidth(), imageDisplay.getHeight());
            scale = new Scale(center, scale.getZoomLevel() / 2);

            render();
        }

        @Override
        public void onTranslate(Vector2d point) {
            Vector2d center = scale.getScreenPointInScale(point, imageDisplay.getWidth(), imageDisplay.getHeight());
            scale = new Scale(center, scale.getZoomLevel());

            render();
        }
    }
}

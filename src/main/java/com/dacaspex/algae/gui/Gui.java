package com.dacaspex.algae.gui;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.gui.display.ImageDisplay;
import com.dacaspex.algae.gui.menu.MenuBar;
import com.dacaspex.algae.gui.menu.event.MenuEventListener;
import com.dacaspex.algae.legacyGui.settings.colorScheme.ColorSchemeSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.FractalSettings;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private final int width;
    private final int height;

    private final Renderer renderer;
    private final MenuBar menuBar;
    private final ImageDisplay imageDisplay;
    private final StatusBar statusBar;

    private Fractal fractal;
    private ColorScheme colorScheme;
    private Scale scale;
    private RenderSettings renderSettings;

    public Gui() {
        this.width = 800;
        this.height = 800;

        this.renderer = new Renderer();
        this.menuBar = new MenuBar();
        this.imageDisplay = new ImageDisplay();
        this.statusBar = new StatusBar();
    }

    public void build() {
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Build menu bar
        menuBar.build();
        menuBar.addEventListener(new MenuBarEventListener());
        setJMenuBar(menuBar);

        // Build image display
        imageDisplay.build();
        add(imageDisplay, BorderLayout.CENTER);

        // Build status bar
        statusBar.build();
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
        pack();
    }

    private class MenuBarEventListener implements MenuEventListener {
        @Override
        public void onFractalSettingsSelected(FractalSettings fractalSettings) {

        }

        @Override
        public void onColorSchemeSettingsSelected(ColorSchemeSettings colorSchemeSettings) {

        }
    }

    private class RendererEventListener extends RendererEventAdapter {
        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {
            imageDisplay.updateImage(event.getImage());
        }
    }
}

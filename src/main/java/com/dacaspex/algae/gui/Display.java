package com.dacaspex.algae.gui;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.Grayscale;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.fractal.JuliaFractal;
import com.dacaspex.algae.gui.menu.MenuBar;
import com.dacaspex.algae.gui.settings.ColorSchemeSettingsDisplay;
import com.dacaspex.algae.gui.settings.colorScheme.GrayscaleSettings;
import com.dacaspex.algae.main.Application;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.render.settings.RenderSettings;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Display extends JFrame implements KeyListener {

    private Panel panel;
    private MenuBar menuBar;
    private ColorSchemeSettingsDisplay colorSchemeSettingsDisplay;
    private BufferedImage image;

    private Fractal activeFractal;
    private ColorScheme activeColorScheme;

    public Display() {
        this.panel = new Panel();
        this.menuBar = new MenuBar(this);
        this.colorSchemeSettingsDisplay = new ColorSchemeSettingsDisplay(this, new GrayscaleSettings());
        this.image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);

        Application.get().getRenderer().setListener(i -> {
            image = i;
            repaint();
        });
        addKeyListener(this);

        build();
        startup();
    }

    public void openFractalSettings() {

    }

    public void openColorSchemeSettings() {
        colorSchemeSettingsDisplay.open();
    }

    public void onColorSchemeSettingsUpdated() {
        Application.get().getRenderer().render(
                new JuliaFractal(new Complex(0.285, 0.01), 512, 2.0),
                colorSchemeSettingsDisplay.getSettings().getColorScheme(),
                new Scale(new Vector2d(), 0.5, 0.002),
                new RenderSettings(800, 800)
        );
    }

    private void build() {
        setPreferredSize(new Dimension(800, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setJMenuBar(menuBar);
        setVisible(true);
        pack();
    }

    private void startup() {
        Application.get().getRenderer().render(
                new JuliaFractal(new Complex(0.285, 0.01), 512, 2.0),
                new Grayscale(512),
                new Scale(new Vector2d(), 0.5, 0.002),
                new RenderSettings(800, 800)
        );
    }

    private class Panel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                Application.get().close();
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

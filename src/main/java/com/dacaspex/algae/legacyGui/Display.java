package com.dacaspex.algae.legacyGui;

import com.dacaspex.algae.legacyGui.menu.MenuBar;
import com.dacaspex.algae.legacyGui.settings.ColorSchemeSettingsDisplay;
import com.dacaspex.algae.legacyGui.settings.ExportSettings;
import com.dacaspex.algae.legacyGui.settings.FractalSettingsDisplay;
import com.dacaspex.algae.legacyGui.settings.colorScheme.GrayscaleSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.FractalSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.JuliaSettings;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Display extends JFrame implements KeyListener {

    private int width, height;
    private Panel panel;
    private MenuBar menuBar;

    private ColorSchemeSettingsDisplay colorSchemeSettingsDisplay;
    private FractalSettingsDisplay fractalSettingsDisplay;
    private ExportSettings exportSettings;

    private BufferedImage image;
    private Scale scale;

    private Renderer renderer;

    private Timer resizeDelayTimer;

    /**
     * Display events:
     * - zoom in
     * - zoom out
     * - translate
     * -> Menubar
     * - fractal
     * - color scheme
     * - scale
     */

    /**
     * Display:
     * - Menubar
     * - Image display
     * - Status bar
     * - Fractal settings
     * - Color scheme settings
     * - Export menu
     * - Animation menu
     */

//    class GUI {
//        private MenuBar menuBar;
//        private ImageDisplay imageDisplay;
//        private StatusBar statusBar;
//    }
//
//    class ImageDisplay {
//        private BufferedImage image;
//
//        public void updateImage(BufferedImage image);
//        public void clearImage(BufferedImage image);
//    }
//
//    class FractalSettingsGui {
//        public void viewSettings(FractalSettings settings);
//        public void getSettings();
//        public void onUpdate();
//    }

    class Algae {

    }

    public Display() {
        this.width = 800;
        this.height = 1000;
        this.panel = new Panel();
        this.menuBar = new MenuBar(this);
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.scale = new Scale(new Vector2d(), 0.5, 0.002);

        // Settings menus
        this.colorSchemeSettingsDisplay = new ColorSchemeSettingsDisplay(this, new GrayscaleSettings());
        this.fractalSettingsDisplay = new FractalSettingsDisplay(this, new JuliaSettings());
        this.exportSettings = new ExportSettings(fractalSettingsDisplay, colorSchemeSettingsDisplay, scale);

        // Resize delay timer
        this.resizeDelayTimer = new Timer(200, e -> {
            render();
            resizeDelayTimer.stop();
        });

        this.renderer = new Renderer();

        renderer.addEventListener(new RendererEventAdapter() {
            @Override
            public void onRenderCompleted(RenderCompletedEvent event) {
                image = event.getImage();
                repaint();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                resizeDelayTimer.restart();
            }
        });
        addKeyListener(this);

        build();
    }

    public FractalSettingsDisplay getFractalSettingsDisplay() {
        return fractalSettingsDisplay;
    }

    public ColorSchemeSettingsDisplay getColorSchemeSettingsDisplay() {
        return colorSchemeSettingsDisplay;
    }

    public void openFractalSettings() {
        fractalSettingsDisplay.open();
    }

    public void openColorSchemeSettings() {
        colorSchemeSettingsDisplay.open();
    }

    public void openExportSettings() {
        exportSettings.open();
    }

    public void render() {
        renderer.render(
                fractalSettingsDisplay.getSettings().getFractal(),
                colorSchemeSettingsDisplay.getSettings().getColorScheme(),
                scale,
                new RenderSettings(panel.getWidth(), panel.getHeight())
        );
    }

    private void build() {
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        add(panel);
        setVisible(true);
        pack();
    }

    private void zoomIn(Vector2d target) {
        Vector2d center = scale.getScreenPointInScale(target, panel.getWidth(), panel.getHeight());
        scale.setCenter(center);
        scale.setZoomLevel(scale.getZoomLevel() * 2);

        render();
    }

    private void zoomOut(Vector2d target) {
        Vector2d center = scale.getScreenPointInScale(target, panel.getWidth(), panel.getHeight());
        scale.setCenter(center);
        scale.setZoomLevel(scale.getZoomLevel() / 2);

        render();
    }

    private void translate(Vector2d target) {
        Vector2d center = scale.getScreenPointInScale(target, panel.getWidth(), panel.getHeight());
        scale.setCenter(center);

        render();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private class Panel extends JPanel {

        public Panel() {
            addMouseListener(new MouseListener());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }

        private class MouseListener extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        zoomIn(new Vector2d(e.getPoint().x, e.getPoint().y));
                        break;

                    case MouseEvent.BUTTON3:
                        zoomOut(new Vector2d(e.getPoint().x, e.getPoint().y));
                        break;

                    case MouseEvent.BUTTON2:
                        translate(new Vector2d(e.getPoint().x, e.getPoint().y));
                        break;

                    default:
                        break;
                }
            }
        }
    }
}

package com.dacaspex.algae.gui.export;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RenderEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PreviewPanel extends JPanel {

    private final Renderer renderer;

    private final Dimension dimension;
    private final JLabel previewText;

    private BufferedImage preview;

    public PreviewPanel(Dimension dimension) {
        this.renderer = new Renderer();
        this.dimension = dimension;
        this.previewText = new JLabel("Loading preview...");
        this.preview = null;
    }

    public void build() {
        setPreferredSize(dimension);
        setBorder(BorderFactory.createTitledBorder("Preview"));
        setLayout(new GridBagLayout());

        add(previewText);

        renderer.addEventListener(new RendererEventListener());
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        if (preview != null) {
            // Center image
            int x = 0;
            int y = 0;

            if (preview.getWidth() < getWidth()) {
                x = (int) (0.5 * getWidth() - 0.5 * preview.getWidth());
            }

            if (preview.getHeight() < getHeight()) {
                y = (int) (0.5 * getHeight() - 0.5 * preview.getHeight());
            }

            graphics.drawImage(preview, x, y, null);
        }
    }

    public void loadPreview(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderSettings) {
        // Adjust size for preview
        int width = renderSettings.width;
        int height = renderSettings.height;

        if (width > getWidth()) {
            float ratio = (float) getWidth() / width;
            width = (int) (width * ratio);
            height = (int) (height * ratio);
        }

        if (height > getHeight()) {
            float ratio = (float) getHeight() / height;
            width = (int) (width * ratio);
            height = (int) (height * ratio);
        }

        // Create new render settings
        renderSettings = new RenderSettings(width, height, 0.5, 1);

        renderer.render(fractal, colorScheme, scale, renderSettings);
    }

    private class RendererEventListener extends RendererEventAdapter {
        @Override
        public void onRenderStarted(RenderEvent event) {
            previewText.setVisible(true);
            preview = null;
            repaint();
        }

        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {
            previewText.setVisible(false);
            preview = event.getImage();
            repaint();
        }
    }
}

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
            graphics.drawImage(preview, 0, 0, null);
        }
    }

    public void loadPreview(
            Fractal fractal,
            ColorScheme colorScheme,
            Scale scale,
            RenderSettings renderSettings
    ) {
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

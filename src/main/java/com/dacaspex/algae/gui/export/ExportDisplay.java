package com.dacaspex.algae.gui.export;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * This is an experimental, first, take on the export GUI. I do want to create a custom UI in the future,
 * but this can do for now.
 */
public class ExportDisplay extends JFrame {

    private final int width;
    private final int height;

    private final Renderer renderer;
    private final Renderer previewRenderer;

    private Fractal fractal;
    private ColorScheme colorScheme;
    private Scale scale;
    private RenderSettings renderSettings;

    /* GUI Components */
    private PreviewPanel previewPanel;

    public ExportDisplay() {
        this.width = 800;
        this.height = 400;
        this.renderer = new Renderer();
        this.previewRenderer = new Renderer();
        this.renderSettings = new RenderSettings(1920, 1080, 1, 1);
    }

    public void build() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TEMP
        setPreferredSize(new Dimension(width, height));
        setTitle("Export as image");

        // Create panels
        JPanel settingsPanel = new JPanel();
        JPanel generalSettingsPanel = new JPanel();
        JPanel actionsPanel = new JPanel();

        // Preview panel
        previewPanel = new PreviewPanel(new Dimension(400, height));
        previewPanel.build();

        // General settings panel
        JLabel widthLabel = new JLabel("Width");
        JLabel heightLabel = new JLabel("Height");
        JTextField widthInput = new JTextField();
        JTextField heightInput = new JTextField();

        generalSettingsPanel.setBorder(BorderFactory.createTitledBorder("General settings"));

        widthInput.setBorder(BorderFactory.createCompoundBorder(
                widthInput.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        heightInput.setBorder(BorderFactory.createCompoundBorder(
                heightInput.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Layout of general settings panel
        GroupLayout layout = new GroupLayout(generalSettingsPanel);
        generalSettingsPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(widthLabel)
                                .addComponent(heightLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(widthInput)
                                .addComponent(heightInput))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(widthLabel)
                                .addComponent(widthInput))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(heightLabel)
                                .addComponent(heightInput))
        );

        // Action panel
        JButton renderButton = new JButton("Render");
        actionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        actionsPanel.add(renderButton);

        // Build layout for gui
        setLayout(new BorderLayout());
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));
        settingsPanel.add(generalSettingsPanel);
        settingsPanel.add(actionsPanel);
        add(settingsPanel, BorderLayout.CENTER);
        add(previewPanel, BorderLayout.EAST);

        pack();
        setVisible(false);

        renderer.addEventListener(new RendererEventListener());
    }

    public void open(Fractal fractal, ColorScheme colorScheme, Scale scale) {
        this.fractal = fractal;
        this.colorScheme = colorScheme;
        this.scale = scale;
        setVisible(true);

        previewPanel.loadPreview(fractal, colorScheme, scale, renderSettings);
    }

    public void close() {
        setVisible(false);
    }

    private class RendererEventListener extends RendererEventAdapter {
        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {

        }
    }

    private class PreviewRendererEventListener extends RendererEventAdapter {
        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {

        }
    }
}

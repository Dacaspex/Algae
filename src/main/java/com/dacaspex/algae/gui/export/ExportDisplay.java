package com.dacaspex.algae.gui.export;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RenderEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is an experimental, first, take on the export GUI. I do want to create a custom UI in the future,
 * but this can do for now.
 */
public class ExportDisplay extends JFrame {

    private final int displayWidth;
    private final int displayHeight;

    private final Renderer renderer;

    private final ReloadPreviewListener reloadPreviewListener;

    /* Defaults for form fields */
    private final int defaultWidth;
    private final int defaultHeight;

    private Fractal fractal;
    private ColorScheme colorScheme;
    private Scale scale;
    private File saveLocation;

    /* GUI Components */
    private PreviewPanel previewPanel;
    private JTextField widthInput;
    private JTextField heightInput;
    private JFileChooser fileChooser;
    private JLabel saveLocationLabel;
    private JButton openFileChooserButton;
    private JButton renderButton;

    public ExportDisplay() {
        this.displayWidth = 800;
        this.displayHeight = 400;
        this.defaultWidth = 1920;
        this.defaultHeight = 1080;
        this.renderer = new Renderer();
        this.reloadPreviewListener = new ReloadPreviewListener();
        this.saveLocation = null;
    }

    public void build() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(displayWidth, displayHeight));
        setTitle("Export as image");

        // Preview panel
        previewPanel = new PreviewPanel(new Dimension(400, displayHeight));
        previewPanel.build();

        // General settings panel
        JPanel generalSettingsPanel = new JPanel();
        JLabel widthLabel = new JLabel("Width");
        JLabel heightLabel = new JLabel("Height");
        widthInput = new JTextField(Integer.toString(defaultWidth));
        widthInput.addActionListener(reloadPreviewListener);
        heightInput = new JTextField(Integer.toString(defaultHeight));
        heightInput.addActionListener(reloadPreviewListener);

        JLabel fileChooserLabel = new JLabel("Save to");
        saveLocationLabel = new JLabel("Choose location...");
        openFileChooserButton = new JButton("Choose location");
        openFileChooserButton.addActionListener(new OpenFileChooserListener());
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

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
                                .addComponent(heightLabel)
                                .addComponent(fileChooserLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(widthInput)
                                .addComponent(heightInput)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(openFileChooserButton)
                                        .addComponent(saveLocationLabel)))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(widthLabel)
                                .addComponent(widthInput))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(heightLabel)
                                .addComponent(heightInput))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(fileChooserLabel)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(openFileChooserButton)
                                        .addComponent(saveLocationLabel)))
        );

        // Action panel
        JPanel actionsPanel = new JPanel();
        renderButton = new JButton("Render");
        renderButton.addActionListener(new RenderActionListener());
        actionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        actionsPanel.add(renderButton);

        // Settings panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.PAGE_AXIS));
        settingsPanel.add(generalSettingsPanel);
        settingsPanel.add(actionsPanel);

        // Gui
        setLayout(new BorderLayout());
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

        previewPanel.loadPreview(fractal, colorScheme, scale, new RenderSettings(defaultWidth, defaultHeight));
    }

    public void close() {
        setVisible(false);
    }

    private class RendererEventListener extends RendererEventAdapter {
        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {
            // Reset button state
            renderButton.setText("Render");
            renderButton.setEnabled(true);
            openFileChooserButton.setEnabled(true);

            // Create file name
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss");
            Date date = new Date();
            String fileName = "export-" + dateFormat.format(date) + ".png";

            File file = new File(saveLocation.getPath(), fileName);

            // Attempt to save the file
            try {
                ImageIO.write(event.getImage(), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onProgress(RenderEvent event) {
            renderButton.setText(event.getProgress() + "%");
        }
    }

    private class RenderActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // Only render when there is a save location
            if (saveLocation == null) {
                // TODO: Show error
                return;
            }

            // Set button state
            renderButton.setEnabled(false);
            renderButton.setText("Starting render...");
            openFileChooserButton.setEnabled(false);

            // Validate data
            int width, height;

            try {
                width = Integer.parseInt(widthInput.getText());
                height = Integer.parseInt(heightInput.getText());
            } catch (NumberFormatException e) {
                // TODO: Show error
                // TODO: This code is copied so it implies we should refactor this code to a better solution
                // Preferably, we want to make sure the input is always correct, or it should display some kind of
                // warning label if it isn't, just like with the property sheet.
                return;
            }

            RenderSettings renderSettings = new RenderSettings(width, height, 1, 1);
            renderer.render(fractal, colorScheme, scale, renderSettings);
        }
    }

    private class OpenFileChooserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int returnVal = fileChooser.showOpenDialog(ExportDisplay.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                saveLocation = fileChooser.getSelectedFile();

                // Set label text to path name
                String path;

                if (saveLocation.toString().length() > 20) {
                    int length = saveLocation.toString().length();
                    path = saveLocation.toString().substring(0, 10)
                            + "..."
                            + saveLocation.toString().substring(length - 10, length - 1);
                } else {
                    path = saveLocation.toString();
                }

                saveLocationLabel.setText(path);
                saveLocationLabel.setToolTipText(saveLocation.toString());
            }
        }
    }

    private class ReloadPreviewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // Validate data
            int width, height;

            try {
                width = Integer.parseInt(widthInput.getText());
                height = Integer.parseInt(heightInput.getText());
            } catch (NumberFormatException e) {
                // TODO: Show error
                return;
            }

            // Data validated, render preview
            previewPanel.loadPreview(fractal, colorScheme, scale, new RenderSettings(width, height));
        }
    }
}

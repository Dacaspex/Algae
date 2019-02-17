package com.dacaspex.algae.gui.colorband;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ColorBandDisplay extends JFrame {

    private final int displayWidth;
    private final int displayHeight;

    private colorBandPanel colorbandPanel;
    private Marker selectedMarker;

    private JButton cancelButton;
    private JButton confirmButton;

    private JLabel positionLabel;
    private JLabel positionValueLabel;
    private JLabel colorLabel;
    private JButton colorPickerButton;

    private JButton removeMarkerButton;
    private JButton addMarkerButton;

    public ColorBandDisplay() {
        this.displayWidth = 400;
        this.displayHeight = 300;
    }

    public void build() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TEMP
        setPreferredSize(new Dimension(displayWidth, displayHeight));
        setTitle("Colourband creator");
        setResizable(false);

        // Build color band panel
        colorbandPanel = new colorBandPanel(displayWidth);
        colorbandPanel.build();
        colorbandPanel.addEventListener(new ColorBandEventListener());

        // Build settings panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setMaximumSize(new Dimension(displayWidth, 150));
        settingsPanel.setLayout(new BorderLayout());

        JPanel colorPickerPanel = new JPanel();
        settingsPanel.add(colorPickerPanel, BorderLayout.CENTER);
        colorPickerPanel.setBorder(BorderFactory.createTitledBorder("Pick colour"));

        JPanel buttonsPanel = new JPanel();
        settingsPanel.add(buttonsPanel, BorderLayout.EAST);

        positionLabel = new JLabel("Position");
        positionValueLabel = new JLabel("None selected");
        colorLabel = new JLabel("Color");
        colorPickerButton = new JButton();
        colorPickerButton.setBackground(Color.LIGHT_GRAY);
        colorPickerButton.addActionListener(new ColorPickerListener());
        colorPickerButton.setMinimumSize(new Dimension(80, 20));

        GroupLayout settingsLayout = new GroupLayout(colorPickerPanel);
        colorPickerPanel.setLayout(settingsLayout);
        settingsLayout.setAutoCreateGaps(true);
        settingsLayout.setAutoCreateContainerGaps(true);

        settingsLayout.setHorizontalGroup(
                settingsLayout.createSequentialGroup()
                        .addGroup(settingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(positionLabel)
                                .addComponent(colorLabel))
                        .addGroup(settingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(positionValueLabel)
                                .addComponent(colorPickerButton))
        );
        settingsLayout.setVerticalGroup(
                settingsLayout.createSequentialGroup()
                        .addGroup(settingsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(positionLabel)
                                .addComponent(positionValueLabel))
                        .addGroup(settingsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(colorLabel)
                                .addComponent(colorPickerButton))
        );

        // Create button panel
        removeMarkerButton = new JButton("Remove marker");
        removeMarkerButton.addActionListener(new RemoveMarkerListener());
        addMarkerButton = new JButton("Add marker button");
        addMarkerButton.addActionListener(new AddMarkerListener());

        GroupLayout buttonsLayout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsLayout);
        buttonsLayout.setAutoCreateGaps(true);
        buttonsLayout.setAutoCreateContainerGaps(true);

        buttonsLayout.setHorizontalGroup(
                buttonsLayout.createParallelGroup()
                        .addComponent(addMarkerButton)
                        .addComponent(removeMarkerButton)
        );
        buttonsLayout.setVerticalGroup(
                buttonsLayout.createSequentialGroup()
                        .addGroup(buttonsLayout.createSequentialGroup()
                                .addComponent(addMarkerButton)
                                .addComponent(removeMarkerButton))
        );

        // Build action panel
        JPanel actionPanel = new JPanel();
        actionPanel.setMaximumSize(new Dimension(displayWidth, 30));
        actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));

        cancelButton = new JButton("Cancel");
        confirmButton = new JButton("Confirm");

        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        LayoutManager layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);

        add(colorbandPanel);
        add(settingsPanel);
        add(actionPanel);

        pack();
        setVisible(false);
    }

    public void updateLabels(Marker marker) {
        NumberFormat formatter = new DecimalFormat("#0.000");
        colorPickerButton.setBackground(marker.color);
        positionValueLabel.setText(formatter.format(marker.position));
    }

    public void open() {
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }

    private class ColorPickerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = JColorChooser.showDialog(ColorBandDisplay.this, "Pick colour", colorPickerButton.getBackground());

            if (color != null && selectedMarker != null) {
                selectedMarker.color = color;
                colorbandPanel.update();
            }
        }
    }

    private class ColorBandEventListener implements com.dacaspex.algae.gui.colorband.ColorBandEventListener {
        @Override
        public void onMarkerSelected(Marker marker) {
            if (marker != null) {
                selectedMarker = marker;
                updateLabels(marker);
            }
        }

        @Override
        public void onMarkerUpdated(Marker marker) {
            updateLabels(marker);
        }
    }

    private class AddMarkerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            colorbandPanel.addMarker();
        }
    }

    private class RemoveMarkerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            colorbandPanel.removeMarker();
        }
    }
}

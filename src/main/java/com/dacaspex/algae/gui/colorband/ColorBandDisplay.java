package com.dacaspex.algae.gui.colorband;

import javax.swing.*;
import java.awt.*;

public class ColorBandDisplay extends JFrame {

    private final int displayWidth;
    private final int displayHeight;

    private colorBandPanel colorbandPanel;

    private JButton cancelButton;
    private JButton confirmButton;

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

        JPanel settingsPanel = new JPanel();
        settingsPanel.setMaximumSize(new Dimension(displayWidth, 150));

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

    public void open() {
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }
}

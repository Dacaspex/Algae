package com.dacaspex.algae.gui.colorband;

import javax.swing.*;
import java.awt.*;

public class colorBandPanel extends JPanel {

    private final int panelWidth;

    /* GUI components */
    private Canvas canvas;

    public colorBandPanel(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public void build() {
        setPreferredSize(new Dimension(panelWidth, 100));
        setMaximumSize(new Dimension(panelWidth, 100));
        setBorder(BorderFactory.createTitledBorder("Edit colour band"));

        // Build canvas
        canvas = new Canvas();

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.GREEN);
            g.fillRect(0, 0, this.getWidth(), 40);
        }
    }
}

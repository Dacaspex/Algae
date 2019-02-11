package com.dacaspex.algae.gui.colorband;

import com.dacaspex.algae.util.ColorBand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class colorBandPanel extends JPanel {

    private final int MIN_MARKER_DISTANCE = 3;

    private final int panelWidth;

    private List<Marker> markers;

    /* GUI components */
    private Canvas canvas;

    public colorBandPanel(int panelWidth) {
        this.panelWidth = panelWidth;
        this.markers = new ArrayList<>();
    }

    public void build() {
        setPreferredSize(new Dimension(panelWidth, 100));
        setMaximumSize(new Dimension(panelWidth, 100));
        setBorder(BorderFactory.createTitledBorder("Edit colour band"));

        // Build canvas
        canvas = new Canvas();
        canvas.build();

        // Initialise color band
        markers.add(new Marker(0, Color.BLUE));
        markers.add(new Marker(0.5, Color.GREEN));
        markers.add(new Marker(1, Color.RED));

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    private class Canvas extends JPanel {
        private boolean dragging;

        public void build() {
            dragging = false;
            addMouseListener(new MouseListener());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            ColorBand colorBand = new ColorBand();
            markers.forEach(m -> colorBand.add(m.position, m.color));

            for (int x = 0; x < this.getWidth(); x++) {
                g.setColor(colorBand.get((double) x / this.getWidth()));
                g.fillRect(x, 0, 1, 40);
            }
        }
    }

    private class Marker {
        private double position;
        private Color color;

        public Marker(double position, Color color) {
            this.position = position;
            this.color = color;
        }
    }

    private class MouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            canvas.dragging = true;
            int mousePosition = mouseEvent.getX();

            // Find closest marker
            Marker candidate = null;
            int candidateDistance = Integer.MAX_VALUE;
            for (Marker m : markers) {
                if (Math.abs(mousePosition - m.position) <= MIN_MARKER_DISTANCE) {
                    if (Math.abs(mousePosition - m.position) < candidateDistance) {
                        candidate = m;
                        candidateDistance = (int) Math.abs(mousePosition - m.position);
                    }
                }
            }

            if (candidate != null) {
                canvas.dragging = true;
                // TODO: ...
            }
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            canvas.dragging = false;
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
            if (canvas.dragging) {
                // ...
            }
        }
    }
}

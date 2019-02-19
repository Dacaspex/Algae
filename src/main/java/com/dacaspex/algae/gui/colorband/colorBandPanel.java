package com.dacaspex.algae.gui.colorband;

import com.dacaspex.algae.util.ColorBand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class colorBandPanel extends JPanel {

    private final int MIN_MARKER_DISTANCE = 10;

    private final int panelWidth;

    private final List<ColorBandEventListener> listeners;

    private ColorBand template;
    private List<Marker> markers;
    private Marker selectedMarker;
    private Marker draggingMarker;

    /* GUI components */
    private Canvas canvas;

    public colorBandPanel(ColorBand colorBand, int panelWidth) {
        this.template = colorBand;
        this.panelWidth = panelWidth;
        this.listeners = new ArrayList<>();
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
        template.getColors().forEach(p -> markers.add(new Marker(p.getKey(), p.getValue())));

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    public void addEventListener(ColorBandEventListener eventListener) {
        listeners.add(eventListener);
    }

    public void update() {
        canvas.repaint();
    }

    public void addMarker() {
        double position = (new Random()).nextDouble();
        markers.add(new Marker(position, Color.WHITE));
        update();
    }

    public void removeMarker() {
        if (this.selectedMarker != null && !selectedMarker.fixed) {
            markers.remove(selectedMarker);
            update();
        }
    }

    private class Canvas extends JPanel {
        public void build() {
            addMouseListener(new MouseListener());
            addMouseMotionListener(new MouseMotionListener());
        }

        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);

            Graphics2D g = (Graphics2D) graphics;

            ColorBand colorBand = new ColorBand();
            markers.forEach(m -> colorBand.add(m.position, m.color));

            for (int x = 0; x < this.getWidth(); x++) {
                g.setColor(colorBand.get((double) x / this.getWidth()));
                g.fillRect(x, 0, 1, 40);
            }

            for (Marker marker : markers) {
                int x = (int) (marker.position * this.getWidth());
                int y = 40;
                int height = 20;
                int width = 10;

                g.setColor(marker.color);
                g.fillPolygon(
                        new int[]{x, x + width, x - width},
                        new int[]{y, y + height, y + height},
                        3
                );
            }
        }

        public Marker getClosestMarker(int x) {
            Marker candidate = null;
            int candidateDistance = Integer.MAX_VALUE;

            for (Marker m : markers) {
                double distance = Math.abs(x - m.position * canvas.getWidth());

                if (distance <= MIN_MARKER_DISTANCE && distance < candidateDistance) {
                    candidate = m;
                    candidateDistance = (int) distance;
                }
            }

            return candidate;
        }
    }

    private class MouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Marker marker = canvas.getClosestMarker(e.getX());

            selectedMarker = marker;

            if (marker == null || !marker.fixed) {
                draggingMarker = marker;
            }

            listeners.forEach(l -> l.onMarkerSelected(marker));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            draggingMarker = null;
        }
    }

    private class MouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (draggingMarker != null) {
                draggingMarker.position = (double) e.getPoint().x / canvas.getWidth();
                repaint();
                listeners.forEach(l -> l.onMarkerUpdated(draggingMarker));
            }
        }
    }
}

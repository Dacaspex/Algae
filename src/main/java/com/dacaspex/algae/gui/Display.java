package com.dacaspex.algae.gui;

import com.dacaspex.algae.main.Application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Display extends JFrame {

    private Panel panel;
    private BufferedImage image;

    public Display() {
        this.panel = new Panel();
        this.image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);

        Application.get().getRenderer().setListener(i -> {
            image = i;
            repaint();
        });

        build();
        startup();
    }

    private void build() {
        setPreferredSize(new Dimension(600, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
        pack();
    }

    private void startup() {

    }

    private class Panel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
    }
}

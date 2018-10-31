package com.dacaspex.algae.gui;

import com.dacaspex.algae.colorScheme.Grayscale;
import com.dacaspex.algae.fractal.JuliaFractal;
import com.dacaspex.algae.main.Application;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.render.settings.RenderSettings;

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
        setPreferredSize(new Dimension(800, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
        pack();
    }

    private void startup() {
        Application.get().getRenderer().render(
                new JuliaFractal(new Complex(0.285, 0.01), 512, 2.0),
                new Grayscale(),
                new Scale(new Vector2d(), 0.5, 0.002),
                new RenderSettings(800, 800)
        );
    }

    private class Panel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
    }
}

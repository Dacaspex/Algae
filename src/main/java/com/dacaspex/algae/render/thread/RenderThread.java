package com.dacaspex.algae.render.thread;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.render.RenderJob;
import com.dacaspex.algae.render.Renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class RenderThread extends Thread {

    private Fractal fractal;
    private ColorScheme colorScheme;
    private Vector2d[][] points;
    private Renderer renderer;

    public RenderThread(Fractal fractal, ColorScheme colorScheme, Vector2d[][] points, Renderer renderer) {
        this.fractal = fractal;
        this.colorScheme = colorScheme;
        this.points = points;
        this.renderer = renderer;
    }

    @Override
    public void run() {
        RenderJob job;
        while ((job = renderer.getJob()) != null) {
            BufferedImage image = new BufferedImage(job.width, job.height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < job.width; x++) {
                for (int y = 0; y < job.height; y++) {
                    int xMapped = job.xStart + x;
                    int yMapped = job.yStart + y;

                    Complex complex = new Complex(points[xMapped][yMapped].x, points[xMapped][yMapped].y);
                    Color color = colorScheme.getColor(fractal.getSequence(complex));
                    image.setRGB(x, y, color.getRGB());
                }
            }

            renderer.submitImage(image, job);
        }
    }
}

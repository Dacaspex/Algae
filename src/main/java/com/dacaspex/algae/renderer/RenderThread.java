package com.dacaspex.algae.renderer;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Vector2d;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderThread implements Runnable {

    private final Fractal fractal;
    private final ColorScheme colorScheme;
    private final Vector2d[][] points;
    private final TaskQueue taskQueue;
    private final ImageCollector collector;

    public RenderThread(
            Fractal fractal,
            ColorScheme colorScheme,
            Vector2d[][] points,
            TaskQueue taskQueue,
            ImageCollector collector
    ) {
        this.fractal = fractal;
        this.colorScheme = colorScheme;
        this.points = points;
        this.taskQueue = taskQueue;
        this.collector = collector;
    }

    @Override
    public void run() {
        RenderTask task;
        while ((task = taskQueue.get()) != null) {
            BufferedImage image = new BufferedImage(task.width, task.height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < task.width; x++) {
                for (int y = 0; y < task.height; y++) {
                    int xMapped = task.xStart + x;
                    int yMapped = task.yStart + y;

                    Complex complex = new Complex(points[xMapped][yMapped].x, points[xMapped][yMapped].y);
                    Color color = colorScheme.getColor(fractal.getSequence(complex));
                    image.setRGB(x, y, color.getRGB());
                }
            }

            collector.submit(task, image);
        }
    }
}

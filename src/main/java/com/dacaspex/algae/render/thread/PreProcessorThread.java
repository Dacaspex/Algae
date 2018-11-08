package com.dacaspex.algae.render.thread;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.render.Renderer;

public class PreProcessorThread extends Thread {

    private PreProcessor preProcessor;
    private Fractal fractal;
    private Vector2d[][] points;
    private Renderer renderer;

    public PreProcessorThread(PreProcessor preProcessor, Fractal fractal, Vector2d[][] points, Renderer renderer) {
        this.preProcessor = preProcessor;
        this.fractal = fractal;
        this.points = points;
        this.renderer = renderer;
    }

    @Override
    public void run() {
        for (int x = 0; x < points.length; x++) {
            for (int y = 0; y < points[x].length; y++) {
                // Construct complex from point
                Complex complex = new Complex(points[x][y].x, points[x][y].y);

                // Pass sequence through pre-processor
                preProcessor.read(complex, fractal.getSequence(complex));
            }
        }

        renderer.preProcessorCompleted();
    }
}

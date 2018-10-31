package com.dacaspex.algae.render;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.render.settings.RenderSettings;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Renderer {

    private RenderCompleteListener listener;

    public void render(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderOptions) {

        // Apply pre-processor
        if (colorScheme.getPreProcessor() != null) {
            Vector2d[][] points = scale.getPoints(
                    renderOptions.getPreProcessorWidth(),
                    renderOptions.getPreProcessorHeight()
            );

            for (int x = 0; x < points.length; x++) {
                for (int y = 0; y < points[x].length; y++) {
                    // Construct complex from point
                    Complex complex = new Complex(points[x][y].x, points[x][y].y);

                    // Pass complex through pre-processor
                    colorScheme.getPreProcessor().read(
                            complex,
                            fractal.getSequence(complex)
                    );
                }
            }
        }

        // TEMP: Eventually this will be moved to a multi-threaded solution
        BufferedImage result = new BufferedImage(
                renderOptions.getRenderWidth(),
                renderOptions.getRenderHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        Vector2d[][] points = scale.getPoints(
                renderOptions.getRenderWidth(),
                renderOptions.getRenderHeight()
        );

        for (int x = 0; x < points.length; x++) {
            for (int y = 0; y < points[x].length; y++) {
                // Construct complex from point
                Complex complex = new Complex(points[x][y].x, points[x][y].y);
                Color color = colorScheme.getColor(fractal.getSequence(complex));
                result.setRGB(x, y, color.getRGB());
            }
        }

        listener.onCompleted(result);
    }

    public void setListener(RenderCompleteListener listener) {
        this.listener = listener;
    }
}

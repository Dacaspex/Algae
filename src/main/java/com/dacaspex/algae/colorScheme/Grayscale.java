package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.util.math.Complex;

import java.awt.Color;
import java.util.List;

public class Grayscale implements ColorScheme {

    private double divisor;
    private boolean inverted;

    public Grayscale(double divisor, boolean inverted) {
        this.divisor = divisor;
        this.inverted = inverted;
    }

    @Override
    public PreProcessor getPreProcessor() {
        return null;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        float brightness = (float) (sequence.size() / divisor);
        brightness = Math.min(brightness, 1);

        if (inverted) {
            brightness = 1 - brightness;
        }

        return Color.getHSBColor(0, 0, brightness);
    }
}

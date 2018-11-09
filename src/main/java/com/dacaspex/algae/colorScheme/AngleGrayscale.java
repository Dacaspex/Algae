package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Vector2d;

import java.awt.Color;
import java.util.List;

public class AngleGrayscale implements ColorScheme {

    private boolean wrap;
    private boolean inverted;

    public AngleGrayscale(boolean wrap, boolean inverted) {
        this.wrap = wrap;
        this.inverted = inverted;
    }

    @Override
    public PreProcessor getPreProcessor() {
        return null;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        Vector2d angleVector = sequence.get(sequence.size() - 1).toVector();
        // Translate angle from [-PI, PI] -> [0, 2PI]
        double angle = angleVector.getAngle() + Math.PI;

        float brightness;
        if (wrap) {
            if (angle <= Math.PI) {
                brightness = (float) (angle / Math.PI);
            } else {
                brightness = 1 - (float) ((angle - Math.PI) / Math.PI);
            }
        } else {
            brightness = (float) (angle / (2 * Math.PI));
        }

        if (inverted) {
            brightness = 1 - brightness;
        }

        return Color.getHSBColor(0, 0, brightness);
    }
}

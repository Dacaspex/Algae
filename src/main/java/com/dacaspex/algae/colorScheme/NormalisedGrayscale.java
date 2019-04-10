package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.colorScheme.preprocessor.SequenceLengthPreProcessor;
import com.dacaspex.algae.util.math.Complex;

import java.awt.Color;
import java.util.List;

public class NormalisedGrayscale implements ColorScheme {

    private boolean inverted;
    private SequenceLengthPreProcessor preProcessor;

    public NormalisedGrayscale(boolean inverted) {
        this.inverted = inverted;
        this.preProcessor = new SequenceLengthPreProcessor();
    }

    @Override
    public PreProcessor getPreProcessor() {
        return preProcessor;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        float brightness = (float) ((sequence.size() - preProcessor.getMinimumLength())
                / (double) (preProcessor.getMaximumLength() - preProcessor.getMinimumLength()));
        brightness = Math.min(Math.max(brightness, 0), 1);

        if (inverted) {
            brightness = 1 - brightness;
        }

        return Color.getHSBColor(0, 0, brightness);
    }
}

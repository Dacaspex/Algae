package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.colorScheme.preprocessor.SequenceLengthPreProcessor;
import com.dacaspex.algae.util.ColorBand;
import com.dacaspex.algae.util.math.Complex;

import java.awt.*;
import java.util.List;

public class LinearInterpolatedColorBand implements ColorScheme {

    private final SequenceLengthPreProcessor preProcessor;
    private final ColorBand colorBand;

    public LinearInterpolatedColorBand(ColorBand colorBand) {
        this.colorBand = colorBand;
        this.preProcessor = new SequenceLengthPreProcessor();
    }

    @Override
    public PreProcessor getPreProcessor() {
        return preProcessor;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        double alpha = (sequence.size() - preProcessor.getMinimumLength())
                / (double) (preProcessor.getMaximumLength() - preProcessor.getMinimumLength());

        // Make sure 0 <= alpha <= 1
        alpha = Math.min(Math.max(0d, alpha), 1d);

        return colorBand.get(alpha);
    }
}

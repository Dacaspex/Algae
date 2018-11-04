package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.math.Complex;

import java.awt.Color;
import java.util.List;

public class Grayscale implements ColorScheme {

    private double divisor;

    public Grayscale(double divisor) {
        this.divisor = divisor;
    }

    @Override
    public PreProcessor getPreProcessor() {
        return null;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        int gray = (int) Math.min((sequence.size() / divisor) * 255, 255);

        return new Color(gray, gray, gray);
    }
}

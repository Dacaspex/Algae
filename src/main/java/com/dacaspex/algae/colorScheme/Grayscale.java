package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.math.Complex;

import java.awt.Color;
import java.util.List;

public class Grayscale implements ColorScheme {

    @Override
    public PreProcessor getPreProcessor() {
        return null;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        int gray = (int) ((sequence.size() / 512.0) * 255);

        return new Color(gray, gray, gray);
    }
}

package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.util.math.Complex;

import java.awt.*;
import java.util.List;

public interface ColorScheme {

    public PreProcessor getPreProcessor();

    public Color getColor(List<Complex> sequence);
}

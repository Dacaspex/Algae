package com.dacaspex.algae.colorScheme.preprocessor;

import com.dacaspex.algae.util.math.Complex;

import java.util.List;

public interface PreProcessor {

    public void read(Complex input, List<Complex> sequence);
}

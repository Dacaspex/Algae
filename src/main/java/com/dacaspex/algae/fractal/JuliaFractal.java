package com.dacaspex.algae.fractal;

import com.dacaspex.algae.util.math.Complex;

import java.util.ArrayList;
import java.util.List;

public class JuliaFractal implements Fractal {

    private final Complex constant;
    private final int maxIterations;
    private final double escapeValue;

    public JuliaFractal(Complex constant, int maxIterations, double escapeValue) {
        this.constant = constant;
        this.maxIterations = maxIterations;
        this.escapeValue = escapeValue;
    }

    public List<Complex> getSequence(Complex c) {
        List<Complex> sequence = new ArrayList<>();
        sequence.add(c);

        // Apply the Julia set calculation
        for (int i = 0; i < maxIterations && c.getModulus() < escapeValue; i++) {
            c = c.powi(2).addi(constant).copy();
            sequence.add(c);
        }

        return sequence;
    }
}

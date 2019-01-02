package com.dacaspex.algae.fractal;

import com.dacaspex.algae.math.Complex;

import java.util.ArrayList;
import java.util.List;

public class MandelbrotFractal implements Fractal {

    private final int maxIterations;
    private final double escapeValue;

    public MandelbrotFractal(int maxIterations, double escapeValue) {
        this.maxIterations = maxIterations;
        this.escapeValue = escapeValue;
    }

    @Override
    public List<Complex> getSequence(Complex c) {
        List<Complex> sequence = new ArrayList<>();
        Complex value = new Complex();

        // Apply mandelbrot set calculation
        for (int i = 0; i < maxIterations && value.getModulus() < escapeValue; i++) {
            value = value.powi(2).addi(c).copy();
            sequence.add(value);
        }

        return sequence;
    }
}

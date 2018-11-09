package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.math.Complex;

import java.awt.Color;
import java.util.List;

public class Wave implements ColorScheme {

    private double frequencyRed;
    private double frequencyGreen;
    private double frequencyBlue;

    private double phaseRed;
    private double phaseGreen;
    private double phaseBlue;

    private double centerRed;
    private double centerGreen;
    private double centerBlue;

    private double deltaRed;
    private double deltaGreen;
    private double deltaBlue;

    public Wave(
            double frequencyRed,
            double frequencyGreen,
            double frequencyBlue,
            double phaseRed,
            double phaseGreen,
            double phaseBlue,
            double centerRed,
            double centerGreen,
            double centerBlue,
            double deltaRed,
            double deltaGreen,
            double deltaBlue
    ) {
        this.frequencyRed = frequencyRed;
        this.frequencyGreen = frequencyGreen;
        this.frequencyBlue = frequencyBlue;
        this.phaseRed = phaseRed;
        this.phaseGreen = phaseGreen;
        this.phaseBlue = phaseBlue;
        this.centerRed = centerRed;
        this.centerGreen = centerGreen;
        this.centerBlue = centerBlue;
        this.deltaRed = deltaRed;
        this.deltaGreen = deltaGreen;
        this.deltaBlue = deltaBlue;
    }

    @Override
    public PreProcessor getPreProcessor() {
        return null;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        int value = sequence.size();

        // Calculate rgb values
        int r = (int) Math.abs((Math.sin(frequencyRed * value + phaseRed) * centerRed + deltaRed));
        int g = (int) Math.abs((Math.sin(frequencyGreen * value + phaseGreen) * centerGreen + deltaGreen));
        int b = (int) Math.abs((Math.sin(frequencyBlue * value + phaseBlue) * centerBlue + deltaBlue));

        // Cap rgb values at 255
        r = Math.min(r, 255);
        g = Math.min(g, 255);
        b = Math.min(b, 255);

        return new Color(r, g, b);
    }
}

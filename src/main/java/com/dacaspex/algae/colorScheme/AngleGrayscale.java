package com.dacaspex.algae.colorScheme;

import com.dacaspex.algae.colorScheme.preprocessor.PreProcessor;
import com.dacaspex.algae.colorScheme.preprocessor.SequenceArgumentPreProcessor;
import com.dacaspex.algae.math.Complex;

import java.awt.*;
import java.util.List;

public class AngleGrayscale implements ColorScheme {

    private boolean wrap;
    private boolean inverted;
    private boolean normalise;
    private AngleType angleType;
    private SequenceArgumentPreProcessor preProcessor;

    public AngleGrayscale(boolean wrap, boolean inverted, boolean normalise, AngleType angleType) {
        this.wrap = wrap;
        this.inverted = inverted;
        this.normalise = normalise;
        this.angleType = angleType;
        this.preProcessor = new SequenceArgumentPreProcessor();
    }

    @Override
    public PreProcessor getPreProcessor() {
        return preProcessor;
    }

    @Override
    public Color getColor(List<Complex> sequence) {
        double brightness = 0;
        switch (angleType) {
            case LAST:
                brightness = calculateLast(sequence);
                break;
            case MAX:
                brightness = calculateMax(sequence);
                break;
            case MIN:
                brightness = calculateMin(sequence);
                break;
            case AVERAGE:
                brightness = calculateAverage(sequence);
                break;
        }

        // Brightness is the range [0, 1]
        if (wrap) {
            // Wrap such that [0, 0.5] -> {0, 1] and (0.5, 1] -> [1, 0]
            brightness = (brightness <= 0.5) ? 2 * brightness : 2 * (1 - brightness);
        }

        return Color.getHSBColor(0, 0, (float) brightness);
    }

    private double calculateLast(List<Complex> sequence) {
        return range(
                handleNormalise(
                        sequence.get(sequence.size() - 1).getArgument(),
                        preProcessor.getMaximumLastArgument(),
                        preProcessor.getMinimumLastArgument()
                )
        );
    }

    private double calculateMax(List<Complex> sequence) {
        double argument = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .max()
                .orElse(0);

        return range(
                handleNormalise(
                        argument,
                        preProcessor.getMaximumMaximumArgument(),
                        preProcessor.getMinimumMaximumArgument()
                )
        );
    }

    private double calculateMin(List<Complex> sequence) {
        double argument = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .min()
                .orElse(0);

        return range(
                handleNormalise(
                        argument,
                        preProcessor.getMaximumMinimumArgument(),
                        preProcessor.getMinimumMinimumArgument()
                )
        );
    }

    private double calculateAverage(List<Complex> sequence) {
        double argument = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .average()
                .orElse(0);

        return range(
                handleNormalise(
                        argument,
                        preProcessor.getMaximumAverageArgument(),
                        preProcessor.getMinimumAverageArgument()
                )
        );
    }

    private double handleNormalise(double argument, double max, double min) {
        // Shift range [-pi, pi] -> [0, 2*pi]
        argument += Math.PI;
        max += Math.PI;
        min += Math.PI;

        return normalise ? (argument - min) / (max - min) : argument / (2 * Math.PI);
    }

    private double range(double brightness) {
        return Math.max(0d, Math.min(brightness, 1d));
    }

    public enum AngleType {
        LAST,
        AVERAGE,
        MAX,
        MIN
    }
}

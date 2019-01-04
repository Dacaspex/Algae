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

//        Vector2d angleVector = sequence.get(sequence.size() - 1).toVector();
//        // Translate angle from [-PI, PI] -> [0, 2PI]
//        double angle = angleVector.getAngle() + Math.PI;
//
//        if (wrap) {
//            if (angle <= Math.PI) {
//                brightness = (float) (angle / Math.PI);
//            } else {
//                brightness = 1 - (float) ((angle - Math.PI) / Math.PI);
//            }
//        } else {
//            brightness = (float) (angle / (2 * Math.PI));
//        }
//
//        if (inverted) {
//            brightness = 1 - brightness;
//        }

        return Color.getHSBColor(0, 0, (float) brightness);
    }

    private double calculateLast(List<Complex> sequence) {
        double argument = sequence.get(sequence.size() - 1).getArgument();

        return (argument + Math.PI) / (2 * Math.PI);
    }

    private double calculateMax(List<Complex> sequence) {
        double argument = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .max()
                .orElse(0);

        return (argument + Math.PI) / (2 * Math.PI);
    }

    private double calculateMin(List<Complex> sequence) {
        double argument = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .min()
                .orElse(0);

        return (argument + Math.PI) / (2 * Math.PI);
    }

    private double calculateAverage(List<Complex> sequence) {
        double argument = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .average()
                .orElse(0);

        return (argument + Math.PI) / (2 * Math.PI);
    }

    public enum AngleType {
        LAST,
        AVERAGE,
        MAX,
        MIN
    }
}

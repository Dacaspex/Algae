package com.dacaspex.algae.colorScheme.preprocessor;

import com.dacaspex.algae.math.Complex;

import java.util.List;

public class SequenceArgumentPreProcessor implements PreProcessor {

    private long entries;

    private double averageLastArgument;
    private double maximumLastArgument;
    private double minimumLastArgument;

    private double averageAverageArgument;
    private double maximumAverageArgument;
    private double minimumAverageArgument;

    private double averageMaximumArgument;
    private double maximumMaximumArgument;
    private double minimumMaximumArgument;

    private double averageMinimumArgument;
    private double maximumMinimumArgument;
    private double minimumMinimumArgument;

    public SequenceArgumentPreProcessor() {
        this.entries = 0;

        this.averageLastArgument = 0;
        this.maximumLastArgument = -Double.MAX_VALUE;
        this.minimumLastArgument = Double.MAX_VALUE;

        this.averageAverageArgument = 0;
        this.maximumAverageArgument = -Double.MAX_VALUE;
        this.minimumAverageArgument = Double.MAX_VALUE;

        this.averageMaximumArgument = 0;
        this.maximumMaximumArgument = -Double.MAX_VALUE;
        this.minimumMaximumArgument = Double.MAX_VALUE;

        this.averageMinimumArgument = 0;
        this.maximumMinimumArgument = -Double.MAX_VALUE;
        this.minimumMinimumArgument = Double.MAX_VALUE;
    }

    public double getMaximumLastArgument() {
        return maximumLastArgument;
    }

    public double getMinimumLastArgument() {
        return minimumLastArgument;
    }

    public double getAverageLastArgument() {
        return averageLastArgument;
    }

    public double getAverageAverageArgument() {
        return averageAverageArgument;
    }

    public double getMaximumAverageArgument() {
        return maximumAverageArgument;
    }

    public double getMinimumAverageArgument() {
        return minimumAverageArgument;
    }

    public double getAverageMaximumArgument() {
        return averageMaximumArgument;
    }

    public double getMaximumMaximumArgument() {
        return maximumMaximumArgument;
    }

    public double getMinimumMaximumArgument() {
        return minimumMaximumArgument;
    }

    public double getAverageMinimumArgument() {
        return averageMinimumArgument;
    }

    public double getMaximumMinimumArgument() {
        return maximumMinimumArgument;
    }

    public double getMinimumMinimumArgument() {
        return minimumMinimumArgument;
    }

    @Override
    public void read(Complex input, List<Complex> sequence) {
        entries++;

        // Argument of last complex value in the sequence
        Complex last = sequence.get(sequence.size() - 1);
        averageLastArgument = (averageLastArgument * (entries - 1) + last.getArgument()) / entries;
        maximumLastArgument = Math.max(last.getArgument(), maximumLastArgument);
        minimumLastArgument = Math.min(last.getArgument(), minimumLastArgument);

        // Average argument of the sequence
        double average = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .average()
                .orElse(0);
        averageAverageArgument = (averageAverageArgument * (entries - 1) + average) / entries;
        maximumAverageArgument = Math.max(average, maximumAverageArgument);
        minimumAverageArgument = Math.min(average, minimumAverageArgument);

        // Maximum argument of the sequence
        double maximum = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .max()
                .orElse(0);
        averageMaximumArgument = (averageMaximumArgument * (entries - 1) + maximum) / entries;
        maximumMaximumArgument = Math.max(maximum, maximumMaximumArgument);
        minimumMaximumArgument = Math.min(maximum, minimumMaximumArgument);

        // Minimum argument of the sequence
        double minimum = sequence
                .stream()
                .mapToDouble(Complex::getArgument)
                .min()
                .orElse(0);
        averageMinimumArgument = (averageMinimumArgument * (entries - 1) + minimum) / entries;
        maximumMinimumArgument = Math.max(minimum, maximumMinimumArgument);
        minimumMinimumArgument = Math.min(minimum, minimumMinimumArgument);
    }
}

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

    private double maximumArgument;
    private double minimumArgument;

    public SequenceArgumentPreProcessor() {
        this.entries = 0;

        this.averageLastArgument = 0;
        this.maximumLastArgument = -Double.MAX_VALUE;
        this.minimumLastArgument = Double.MAX_VALUE;

        this.averageAverageArgument = 0;
        this.maximumAverageArgument = -Double.MAX_VALUE;
        this.minimumAverageArgument = Double.MAX_VALUE;
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
    }
}

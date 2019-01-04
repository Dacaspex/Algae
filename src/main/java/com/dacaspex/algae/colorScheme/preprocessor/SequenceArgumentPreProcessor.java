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

        Complex last = sequence.get(sequence.size() - 1);
        averageLastArgument =
        maximumLastArgument = Math.max(last.getArgument(), maximumLastArgument);
        minimumLastArgument = Math.min(last.getArgument(), minimumLastArgument);

    }
}

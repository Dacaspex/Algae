package com.dacaspex.algae.colorScheme.preprocessor;

import com.dacaspex.algae.math.Complex;

import java.util.List;

public class SequenceLengthPreProcessor implements PreProcessor {

    private int minimumLength;
    private int maximumLength;
    private long totalLength;
    private int nrEntries;

    public SequenceLengthPreProcessor() {
        minimumLength = Integer.MAX_VALUE;
        maximumLength = Integer.MIN_VALUE;
        totalLength = 0;
        nrEntries = 0;
    }

    public int getMinimumLength() {
        return minimumLength;
    }

    public int getMaximumLength() {
        return maximumLength;
    }

    public double getAverageLength() {
        return (double) totalLength / (double) nrEntries;
    }

    @Override
    public void read(Complex input, List<Complex> sequence) {
        nrEntries++;

        if (sequence.size() < minimumLength) {
            minimumLength = sequence.size();
        }

        if (sequence.size() > maximumLength) {
            maximumLength = sequence.size();
        }

        totalLength += sequence.size();
    }
}

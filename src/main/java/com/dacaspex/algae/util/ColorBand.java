package com.dacaspex.algae.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorBand {

    private List<Pair<Double, Color>> colors;

    public ColorBand(List<Pair<Double, Color>> colors) {
        this.colors = colors;

        this.sort();
    }

    public ColorBand() {
        this(new ArrayList<>());
    }

    public List<Pair<Double, Color>> getColors() {
        return colors;
    }

    public void add(double position, Color color) {
        colors.add(new Pair<>(position, color));
        sort();
    }

    public Color get(double alpha) {
        if (colors.size() == 0) {
            throw new IllegalArgumentException("Colour interpolation cannot consist of zero colours");
        }

        if (colors.size() == 1) {
            return colors.get(0).getValue();
        }

        int index = 0;
        Pair<Double, Color> right = colors.get(0);
        while (right.getKey() <= alpha) {
            right = colors.get(++index);
        }

        Pair<Double, Color> left = colors.get(index - 1);

        double intervalLength = right.getKey() - left.getKey();
        double alphaProjected = (alpha - left.getKey()) / intervalLength;

        return ColorInterpolator.interpolate(left.getValue(), right.getValue(), alphaProjected);
    }

    private void sort() {
        colors.sort((a, b) -> {
            if (a.getKey().equals(b.getKey())) {
                return 0;
            }

            return (a.getKey() < b.getKey()) ? -1 : 1;
        });
    }
}

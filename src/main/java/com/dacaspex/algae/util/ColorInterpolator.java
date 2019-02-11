package com.dacaspex.algae.util;

import java.awt.*;

public class ColorInterpolator {

    /**
     * Interpolates between two colours and a value alpha, 0 <= alpha <= 1
     *
     * @param a     First colour
     * @param b     Second colour
     * @param alpha Alpha, between 0 and 1, inclusive
     * @return Interpolated colour
     */
    public static Color interpolate(Color a, Color b, double alpha) {
        if (alpha < 0 || alpha > 1) {
            throw new IllegalArgumentException("Alpha must be between 0 and 1");
        }

        return new Color(
                (int) ((1 - alpha) * a.getRed() + alpha * b.getRed()),
                (int) ((1 - alpha) * a.getGreen() + alpha * b.getGreen()),
                (int) ((1 - alpha) * a.getBlue() + alpha * b.getBlue())
        );
    }
}

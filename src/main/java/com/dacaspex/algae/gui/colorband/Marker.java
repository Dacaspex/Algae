package com.dacaspex.algae.gui.colorband;

import java.awt.*;

public class Marker {
    public double position;
    public Color color;
    public boolean fixed;

    public Marker(double position, Color color, boolean fixed) {
        this.position = position;
        this.color = color;
        this.fixed = fixed;
    }

    public Marker(double position, Color color) {
        this(position, color, false);
    }
}

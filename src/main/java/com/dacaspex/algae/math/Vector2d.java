package com.dacaspex.algae.math;

public class Vector2d {

    public double x;
    public double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d() {
        this(0, 0);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d addi(Vector2d other) {
        x += other.x;
        y += other.y;

        return this;
    }

    public Vector2d sub(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d subi(Vector2d other) {
        x -= other.x;
        y -= other.y;

        return this;
    }

    public Vector2d mul(double a) {
        return new Vector2d(a * x, a * y);
    }

    public Vector2d muli(double a) {
        x *= a;
        y *= a;

        return this;
    }

    public Vector2d copy() {
        return new Vector2d(x, y);
    }
}
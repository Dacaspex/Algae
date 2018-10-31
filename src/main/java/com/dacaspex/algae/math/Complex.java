package com.dacaspex.algae.math;

public class Complex {

    public double real;
    public double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex() {
        this(0, 0);
    }

    public double getModulus() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
    }

    public double getArgument() {
        if (real > 0) {
            return Math.atan(imaginary / real);
        } else if (real < 0 && imaginary >= 0) {
            return Math.atan(imaginary / real) + Math.PI;
        } else if (real < 0 && imaginary < 0) {
            return Math.atan(imaginary / real) - Math.PI;
        } else if (real == 0 && imaginary > 0) {
            return Math.PI / 2;
        } else if (real == 0 && imaginary < 0) {
            return -(Math.PI / 2);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public Complex add(Complex other) {
        return new Complex(real + other.real, imaginary + other.imaginary);
    }

    public Complex addi(Complex other) {
        real += other.real;
        imaginary += other.imaginary;

        return this;
    }

    public Complex sub(Complex other) {
        return new Complex(real - other.real, imaginary - other.imaginary);
    }

    public Complex subi(Complex other) {
        real -= other.real;
        imaginary -= other.imaginary;

        return this;
    }

    public Complex mul(Complex other) {
        return new Complex(
                this.real * other.real - this.imaginary * other.imaginary,
                this.real * other.imaginary + this.imaginary * other.real
        );
    }

    public Complex muli(Complex other) {
        double real = this.real * other.real - this.imaginary * other.imaginary;
        double imaginary = this.real * other.imaginary + this.imaginary * other.real;

        this.real = real;
        this.imaginary = imaginary;

        return this;
    }

    public Complex mul(double k) {
        return new Complex(real * k, imaginary * k);
    }

    public Complex muli(double k) {
        real *= k;
        imaginary *= k;

        return this;
    }

    public Complex pow(int k) {
        for (int i = 0; i < k; i++) {
            mul(this);
        }

        return this.clone();
    }

    public Complex powi(int k) {
        for (int i = 0; i < k; i++) {
            mul(this);
        }

        return this;
    }

    @Override
    public Complex clone() {
        return new Complex(real, imaginary);
    }
}

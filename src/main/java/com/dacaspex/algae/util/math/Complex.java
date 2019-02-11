package com.dacaspex.algae.util.math;

public class Complex {

    public double real;
    public double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(Vector2d vector) {
        this(vector.x, vector.y);
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

        return this.copy();
    }

    public Complex powi(int k) {
        for (int i = 0; i < k - 1; i++) {
            muli(this);
        }

        return this;
    }

    public Complex abs() {
        return new Complex(
                Math.abs(real),
                Math.abs(imaginary)
        );
    }

    public Complex absi() {
        real = Math.abs(real);
        imaginary = Math.abs(imaginary);

        return this;
    }

    public Complex copy() {
        return new Complex(real, imaginary);
    }

    public Vector2d toVector() {
        return new Vector2d(real, imaginary);
    }

    @Override
    public String toString() {
        String realSign = real >= 0 ? "" : "-";
        String imaginarySign = imaginary >= 0 ? "+" : "-";

        return realSign + real + imaginarySign + imaginary + "i";
    }

    public static Complex parseComplex(String value) {
        // TODO: For god sake please refactor this at some point...
        if (value.chars().filter(ch -> ch == '+').count() + value.chars().filter(ch -> ch == '-').count() > 2) {
            throw new NumberFormatException();
        }

        if (value.equals("")) {
            throw new NumberFormatException();
        }

        String splitter = "+";
        double real = Double.NaN;
        double imaginary = Double.NaN;
        boolean realIsPositive = true;
        boolean imaginaryIsPositive = true;

        if (value.subSequence(0, 1).toString().contains("-")) {
            realIsPositive = false;
        }

        if (value.substring(1).contains("-")) {
            splitter = "-";
            imaginaryIsPositive = false;
        }

        String[] tokens = value.substring(1).replaceAll(",", ".").split("[" + splitter + "]");

        for (String token : tokens) {
            if (token.contains("i") && token.indexOf("i") == token.length() - 1) {
                String parsedToken = (String) token.subSequence(0, token.length() - 1);
                imaginary = Double.parseDouble(parsedToken);
            } else {
                real = Double.parseDouble(token);
            }
        }

        if (!realIsPositive) {
            real = -real;
        }

        if (!imaginaryIsPositive) {
            imaginary = -imaginary;
        }

        return new Complex(real, imaginary);
    }
}

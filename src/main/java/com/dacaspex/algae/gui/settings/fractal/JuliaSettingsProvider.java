package com.dacaspex.algae.gui.settings.fractal;

import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.fractal.JuliaFractal;
import com.dacaspex.algae.gui.settings.util.ComplexProperty;
import com.dacaspex.algae.util.math.Complex;
import com.dacaspex.propertysheet.property.DoubleProperty;
import com.dacaspex.propertysheet.property.IntegerProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleValidatorFactory;
import com.dacaspex.propertysheet.validator.integer.IntegerValidatorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JuliaSettingsProvider implements FractalSettingsProvider {

    private ComplexProperty constantProperty;
    private IntegerProperty maxIterationsProperty;
    private DoubleProperty escapeValueProperty;

    public JuliaSettingsProvider() {
        this.constantProperty = new ComplexProperty("Constant", new Complex(0.285, 0.01));
        this.maxIterationsProperty = new IntegerProperty(
                "Maximum iterations",
                512,
                new IntegerValidatorFactory()
                        .setLowerBound(0)
                        .build()
        );
        this.escapeValueProperty = new DoubleProperty(
                "Escape value",
                2.0,
                new DoubleValidatorFactory()
                        .setLowerBound(0.0)
                        .build()
        );
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                constantProperty,
                maxIterationsProperty,
                escapeValueProperty
        ));
    }

    @Override
    public Fractal getFractal() {
        return new JuliaFractal(
                constantProperty.getValue(),
                maxIterationsProperty.getValue(),
                escapeValueProperty.getValue()
        );
    }
}

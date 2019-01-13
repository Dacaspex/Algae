package com.dacaspex.algae.legacyGui.settings.fractal;

import com.dacaspex.algae.fractal.BurningShipFractal;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.propertysheet.property.DoubleProperty;
import com.dacaspex.propertysheet.property.IntegerProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleValidatorFactory;
import com.dacaspex.propertysheet.validator.integer.IntegerValidatorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BurningShipSettings implements FractalSettings {

    private final IntegerProperty maxIterationsProperty;
    private final DoubleProperty escapeValueProperty;

    public BurningShipSettings() {
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
                maxIterationsProperty,
                escapeValueProperty
        ));
    }

    @Override
    public Fractal getFractal() {
        return new BurningShipFractal(
                maxIterationsProperty.getValue(),
                escapeValueProperty.getValue()
        );
    }
}

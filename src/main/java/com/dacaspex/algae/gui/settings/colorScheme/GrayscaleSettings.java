package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.Grayscale;
import com.dacaspex.propertysheet.property.DoubleProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleValidatorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrayscaleSettings implements ColorSchemeSettings {

    private DoubleProperty divisorProperty;

    public GrayscaleSettings() {
        this.divisorProperty = new DoubleProperty(
                "Divisor",
                512.0,
                new DoubleValidatorFactory()
                        .setLowerBound(1)
                        .includeBounds(true, false)
                        .build()
        );
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                divisorProperty
        ));
    }

    @Override
    public ColorScheme getColorScheme() {
        return new Grayscale(divisorProperty.getValue());
    }
}

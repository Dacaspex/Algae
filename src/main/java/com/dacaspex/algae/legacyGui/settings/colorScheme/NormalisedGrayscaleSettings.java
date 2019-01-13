package com.dacaspex.algae.legacyGui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.NormalisedGrayscale;
import com.dacaspex.propertysheet.property.BooleanProperty;
import com.dacaspex.propertysheet.property.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalisedGrayscaleSettings implements ColorSchemeSettings {

    private BooleanProperty invertedProperty;

    public NormalisedGrayscaleSettings() {
        this.invertedProperty = new BooleanProperty("Inverted", false);
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                invertedProperty
        ));
    }

    @Override
    public ColorScheme getColorScheme() {
        return new NormalisedGrayscale(invertedProperty.getValue());
    }
}

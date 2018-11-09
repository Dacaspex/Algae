package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.AngleGrayscale;
import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.propertysheet.property.BooleanProperty;
import com.dacaspex.propertysheet.property.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AngleGrayscaleSettings implements ColorSchemeSettings {

    private BooleanProperty wrapProperty;
    private BooleanProperty invertedProperty;

    public AngleGrayscaleSettings() {
        this.wrapProperty = new BooleanProperty("Wrap", false);
        this.invertedProperty = new BooleanProperty("Inverted", false);
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                invertedProperty,
                wrapProperty
        ));
    }

    @Override
    public ColorScheme getColorScheme() {
        return new AngleGrayscale(wrapProperty.getValue(), invertedProperty.getValue());
    }
}

package com.dacaspex.algae.legacyGui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.AngleGrayscale;
import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.propertysheet.property.BooleanProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.property.selection.Item;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AngleGrayscaleSettings implements ColorSchemeSettings {

    private BooleanProperty wrapProperty;
    private BooleanProperty invertedProperty;
    private BooleanProperty normaliseProperty;
    private SelectionProperty angleTypeProperty;

    public AngleGrayscaleSettings() {
        this.wrapProperty = new BooleanProperty("Wrap", false);
        this.invertedProperty = new BooleanProperty("Inverted", false);
        this.normaliseProperty = new BooleanProperty("Normalise", false);
        this.angleTypeProperty = new SelectionProperty<>(
                "Angle type",
                new ArrayList<>(
                        Arrays.asList(
                                new Item<>(AngleGrayscale.AngleType.LAST, "Last"),
                                new Item<>(AngleGrayscale.AngleType.AVERAGE, "Average"),
                                new Item<>(AngleGrayscale.AngleType.MAX, "Maximum"),
                                new Item<>(AngleGrayscale.AngleType.MIN, "Minimum")
                        )
                )
        );
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                invertedProperty,
                wrapProperty,
                normaliseProperty,
                angleTypeProperty
        ));
    }

    @Override
    public ColorScheme getColorScheme() {
        return new AngleGrayscale(
                wrapProperty.getValue(),
                invertedProperty.getValue(),
                normaliseProperty.getValue(),
                (AngleGrayscale.AngleType) angleTypeProperty.getValue()
        );
    }
}

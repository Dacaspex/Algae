package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.Grayscale;
import com.dacaspex.propertysheet.property.BooleanProperty;
import com.dacaspex.propertysheet.property.DoubleProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleValidatorFactory;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrayscaleSettingsProvider implements ColorSchemeSettingsProvider {

    private final String identification = "colorscheme.grayscale";

    private DoubleProperty divisorProperty;
    private BooleanProperty invertedProperty;

    public GrayscaleSettingsProvider() {
        this.divisorProperty = new DoubleProperty(
                "Divisor",
                512.0,
                new DoubleValidatorFactory()
                        .setLowerBound(1)
                        .includeBounds(true, false)
                        .build()
        );
        this.invertedProperty = new BooleanProperty("Inverted", false);
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                divisorProperty,
                invertedProperty
        ));
    }

    @Override
    public ColorScheme getColorScheme() {
        return new Grayscale(divisorProperty.getValue(), invertedProperty.getValue());
    }

    @Override
    public JSONObject exportSettings() {
        JSONObject data = new JSONObject();

        data.put("id", identification);
        data.put("divisor", divisorProperty.getValue());
        data.put("inverted", invertedProperty.getValue());

        return data;
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public void importSettings(JSONObject data) throws Exception {
        double divisor = data.getDouble("divisor");
        boolean inverted = data.getBoolean("inverted");

        divisorProperty.setValue(divisor);
        invertedProperty.setValue(inverted);
    }
}

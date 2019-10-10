package com.dacaspex.algae.gui.settings.fractal;

import com.dacaspex.algae.fractal.BurningShipFractal;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.propertysheet.property.DoubleProperty;
import com.dacaspex.propertysheet.property.IntegerProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleValidatorFactory;
import com.dacaspex.propertysheet.validator.integer.IntegerValidatorFactory;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BurningShipSettingsProvider implements FractalSettingsProvider {

    private final String identification = "fractal.burningship";

    private final IntegerProperty maxIterationsProperty;
    private final DoubleProperty escapeValueProperty;

    public BurningShipSettingsProvider() {
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

    @Override
    public JSONObject exportSettings() {
        JSONObject data = new JSONObject();

        data.put("id", identification);
        data.put("max_iterations", maxIterationsProperty.getValue());
        data.put("escape_value", escapeValueProperty.getValue());

        return data;
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public void importSettings(JSONObject data) throws Exception {
        int maxIterations = data.getInt("max_iterations");
        double escapeValue = data.getDouble("escape_value");

        maxIterationsProperty.setValue(maxIterations);
        escapeValueProperty.setValue(escapeValue);
    }
}

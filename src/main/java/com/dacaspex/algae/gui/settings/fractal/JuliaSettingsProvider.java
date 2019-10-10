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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JuliaSettingsProvider implements FractalSettingsProvider {

    private final String identification = "fractal.julia";

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

    @Override
    public JSONObject exportSettings() {
        JSONObject data = new JSONObject();

        JSONObject complexData = new JSONObject();
        complexData.put("real", constantProperty.getValue().real);
        complexData.put("imaginary", constantProperty.getValue().imaginary);

        data.put("id", identification);
        data.put("complex", complexData);
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
        JSONObject complexData = data.getJSONObject("complex");
        Complex complex = new Complex(complexData.getDouble("real"), complexData.getDouble("imaginary"));

        int maxIterations = data.getInt("max_iterations");
        double escapeValue = data.getDouble("escape_value");

        constantProperty.setValue(complex);
        maxIterationsProperty.setValue(maxIterations);
        escapeValueProperty.setValue(escapeValue);
    }
}

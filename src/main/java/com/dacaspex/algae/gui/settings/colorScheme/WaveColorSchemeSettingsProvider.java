package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.Wave;
import com.dacaspex.propertysheet.property.DoubleProperty;
import com.dacaspex.propertysheet.property.Property;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveColorSchemeSettingsProvider implements ColorSchemeSettingsProvider {

    private final String identification = "colorscheme.wave";

    private DoubleProperty frequencyRedProperty;
    private DoubleProperty frequencyBlueProperty;
    private DoubleProperty frequencyGreenProperty;

    private DoubleProperty phaseRedProperty;
    private DoubleProperty phaseBlueProperty;
    private DoubleProperty phaseGreenProperty;

    private DoubleProperty centerRedProperty;
    private DoubleProperty centerBlueProperty;
    private DoubleProperty centerGreenProperty;

    private DoubleProperty deltaRedProperty;
    private DoubleProperty deltaBlueProperty;
    private DoubleProperty deltaGreenProperty;

    public WaveColorSchemeSettingsProvider() {
        this.frequencyRedProperty = new DoubleProperty("Frequency red", 0.016);
        this.frequencyGreenProperty = new DoubleProperty("Frequency blue", 0.013);
        this.frequencyBlueProperty = new DoubleProperty("Frequency green", 0.01);

        this.phaseRedProperty = new DoubleProperty("phase red", 4d);
        this.phaseGreenProperty = new DoubleProperty("phase blue", 2d);
        this.phaseBlueProperty = new DoubleProperty("phase green", 1d);

        this.centerRedProperty = new DoubleProperty("Center red", 230d);
        this.centerGreenProperty = new DoubleProperty("Center blue", 230d);
        this.centerBlueProperty = new DoubleProperty("Center green", 230d);

        this.deltaRedProperty = new DoubleProperty("Delta red", 25d);
        this.deltaGreenProperty = new DoubleProperty("Delta blue", 25d);
        this.deltaBlueProperty = new DoubleProperty("Delta green", 25d);
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                frequencyRedProperty,
                frequencyGreenProperty,
                frequencyBlueProperty,
                phaseRedProperty,
                phaseGreenProperty,
                phaseBlueProperty,
                centerRedProperty,
                centerGreenProperty,
                centerBlueProperty,
                deltaRedProperty,
                deltaGreenProperty,
                deltaBlueProperty
        ));
    }

    @Override
    public ColorScheme getColorScheme() {
        return new Wave(
                frequencyRedProperty.getValue(),
                frequencyGreenProperty.getValue(),
                frequencyBlueProperty.getValue(),
                phaseRedProperty.getValue(),
                phaseGreenProperty.getValue(),
                phaseBlueProperty.getValue(),
                centerRedProperty.getValue(),
                centerGreenProperty.getValue(),
                centerBlueProperty.getValue(),
                deltaRedProperty.getValue(),
                deltaGreenProperty.getValue(),
                deltaBlueProperty.getValue()
        );
    }

    @Override
    public JSONObject exportSettings() {
        JSONObject data = new JSONObject();

        data.put("id", identification);

        JSONObject frequencyData = new JSONObject();
        frequencyData.put("red", frequencyRedProperty.getValue());
        frequencyData.put("green", frequencyGreenProperty.getValue());
        frequencyData.put("blue", frequencyBlueProperty.getValue());

        JSONObject phaseData = new JSONObject();
        phaseData.put("red", phaseRedProperty.getValue());
        phaseData.put("green", phaseGreenProperty.getValue());
        phaseData.put("blue", phaseBlueProperty.getValue());

        JSONObject centerData = new JSONObject();
        centerData.put("red", centerRedProperty.getValue());
        centerData.put("green", centerGreenProperty.getValue());
        centerData.put("blue", centerBlueProperty.getValue());

        JSONObject deltaData = new JSONObject();
        deltaData.put("red", deltaRedProperty.getValue());
        deltaData.put("green", deltaGreenProperty.getValue());
        deltaData.put("blue", deltaBlueProperty.getValue());

        data.put("frequency", frequencyData);
        data.put("phase", phaseData);
        data.put("center", centerData);
        data.put("delta", deltaData);

        return data;
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public void importSettings(JSONObject data) throws Exception {
        JSONObject frequencyData = data.getJSONObject("frequency");
        JSONObject phaseData = data.getJSONObject("phase");
        JSONObject centerData = data.getJSONObject("center");
        JSONObject deltaData = data.getJSONObject("delta");

        double frequencyRed = frequencyData.getDouble("red");
        double frequencyGreen = frequencyData.getDouble("green");
        double frequencyBlue = frequencyData.getDouble("blue");

        frequencyRedProperty.setValue(frequencyRed);
        frequencyGreenProperty.setValue(frequencyGreen);
        frequencyBlueProperty.setValue(frequencyBlue);

        double phaseRed = phaseData.getDouble("red");
        double phaseGreen = phaseData.getDouble("green");
        double phaseBlue = phaseData.getDouble("blue");

        phaseRedProperty.setValue(phaseRed);
        phaseGreenProperty.setValue(phaseGreen);
        phaseBlueProperty.setValue(phaseBlue);

        double centerRed = centerData.getDouble("red");
        double centerGreen = centerData.getDouble("green");
        double centerBlue = centerData.getDouble("blue");

        centerRedProperty.setValue(centerRed);
        centerGreenProperty.setValue(centerGreen);
        centerBlueProperty.setValue(centerBlue);

        double deltaRed = deltaData.getDouble("red");
        double deltaGreen = deltaData.getDouble("green");
        double deltaBlue = deltaData.getDouble("blue");

        deltaRedProperty.setValue(deltaRed);
        deltaGreenProperty.setValue(deltaGreen);
        deltaBlueProperty.setValue(deltaBlue);
    }
}

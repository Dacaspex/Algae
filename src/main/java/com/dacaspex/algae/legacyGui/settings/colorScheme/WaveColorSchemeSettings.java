package com.dacaspex.algae.legacyGui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.Wave;
import com.dacaspex.propertysheet.property.DoubleProperty;
import com.dacaspex.propertysheet.property.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveColorSchemeSettings implements ColorSchemeSettings {

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

    public WaveColorSchemeSettings() {
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
}

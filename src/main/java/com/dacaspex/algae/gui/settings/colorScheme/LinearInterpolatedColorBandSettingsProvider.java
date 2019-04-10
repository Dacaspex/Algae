package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.LinearInterpolatedColorBand;
import com.dacaspex.algae.gui.settings.util.ColorBandProperty;
import com.dacaspex.algae.util.ColorBand;
import com.dacaspex.algae.util.Pair;
import com.dacaspex.propertysheet.property.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearInterpolatedColorBandSettingsProvider implements ColorSchemeSettingsProvider {

    private ColorBandProperty colorBandProperty;

    public LinearInterpolatedColorBandSettingsProvider() {
        this.colorBandProperty = new ColorBandProperty(
                "Color band",
                new ColorBand(new ArrayList<>(Arrays.asList(
                        new Pair<>(0d, Color.BLACK),
                        new Pair<>(1d, Color.BLUE)
                )))
        );
    }

    @Override
    public List<Property> getProperties() {
        return new ArrayList<>(Arrays.asList(
                colorBandProperty
        ));
    }

    @Override
    public ColorScheme getColorScheme() {
        return new LinearInterpolatedColorBand(colorBandProperty.getValue());
    }
}

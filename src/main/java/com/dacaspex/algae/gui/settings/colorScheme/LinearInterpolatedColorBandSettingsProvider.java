package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.colorScheme.LinearInterpolatedColorBand;
import com.dacaspex.algae.gui.settings.util.ColorBandProperty;
import com.dacaspex.algae.util.ColorBand;
import com.dacaspex.algae.util.Pair;
import com.dacaspex.propertysheet.property.Property;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearInterpolatedColorBandSettingsProvider implements ColorSchemeSettingsProvider {

    private final String identification = "colorscheme.linearinterpolatedcolorband";

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

    @Override
    public JSONObject exportSettings() {
        JSONObject data = new JSONObject();

        JSONArray colors = new JSONArray();
        for (Pair<Double, Color> pair : colorBandProperty.getValue().getColors()) {
            JSONObject pairData = new JSONObject();
            pairData.put("position", pair.getKey());
            pairData.put("color", pair.getValue().getRGB());
            colors.put(pairData);
        }

        data.put("id", identification);
        data.put("colors", colors);

        return data;
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public void importSettings(JSONObject data) throws Exception {
        JSONArray colors = data.getJSONArray("colors");

        ColorBand colorBand = new ColorBand();
        for (int i = 0; i < colors.length(); i++) {
            JSONObject pairData = colors.getJSONObject(i);
            double position = pairData.getDouble("position");
            Color color = new Color(pairData.getInt("color"));
            colorBand.add(position, color);
        }

        colorBandProperty.setValue(colorBand);
    }
}

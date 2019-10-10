package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.AngleGrayscale;
import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.propertysheet.property.BooleanProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.property.selection.Item;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AngleGrayscaleSettingsProvider implements ColorSchemeSettingsProvider {

    private final String identification = "colorscheme.anglegrayscale";

    private BooleanProperty wrapProperty;
    private BooleanProperty invertedProperty;
    private BooleanProperty normaliseProperty;
    private SelectionProperty<AngleGrayscale.AngleType> angleTypeProperty;

    public AngleGrayscaleSettingsProvider() {
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
                angleTypeProperty.getValue()
        );
    }

    @Override
    public JSONObject exportSettings() {
        JSONObject data = new JSONObject();

        data.put("id", identification);
        data.put("wrap", wrapProperty.getValue());
        data.put("inverted", invertedProperty.getValue());
        data.put("normalise", normaliseProperty.getValue());
        data.put("angle_type", angleTypeProperty.getValue());

        return data;
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public void importSettings(JSONObject data) throws Exception {
        boolean wrap = data.getBoolean("wrap");
        boolean inverted = data.getBoolean("inverted");
        boolean normalise = data.getBoolean("normalise");
        AngleGrayscale.AngleType angleType = AngleGrayscale.AngleType.valueOf(data.getString("angle_type"));

        wrapProperty.setValue(wrap);
        invertedProperty.setValue(inverted);
        normaliseProperty.setValue(normalise);
        angleTypeProperty.setValue(angleType);
    }
}

package com.dacaspex.algae.gui.settings.colorScheme;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.propertysheet.property.Property;

import java.util.List;

public interface ColorSchemeSettings {

    public List<Property> getProperties();

    public ColorScheme getColorScheme();
}

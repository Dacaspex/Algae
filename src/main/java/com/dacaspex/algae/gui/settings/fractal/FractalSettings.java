package com.dacaspex.algae.gui.settings.fractal;

import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.propertysheet.property.Property;

import java.util.List;

public interface FractalSettings {

    public List<Property> getProperties();

    public Fractal getColorScheme();
}

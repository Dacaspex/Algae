package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.util.ColorBand;
import com.dacaspex.propertysheet.property.AbstractProperty;
import com.dacaspex.propertysheet.validator.NullValidator;

public class ColorBandProperty extends AbstractProperty<ColorBand> {
    public ColorBandProperty(String name, ColorBand value) {
        super(name, value, new NullValidator());
    }
}

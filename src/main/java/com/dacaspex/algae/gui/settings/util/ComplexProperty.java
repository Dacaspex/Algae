package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.util.math.Complex;
import com.dacaspex.propertysheet.property.AbstractProperty;

public class ComplexProperty extends AbstractProperty<Complex> {

    public ComplexProperty(String name, Complex value) {
        super(name, value, new ComplexValidator());
    }
}
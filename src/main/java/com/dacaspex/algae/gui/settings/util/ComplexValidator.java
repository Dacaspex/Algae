package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.math.Complex;
import com.dacaspex.propertysheet.validator.Validator;

public class ComplexValidator implements Validator {

    @Override
    public boolean validate(Object o) {
        String value = (String) o;

        try {
            Complex.parseComplex(value);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
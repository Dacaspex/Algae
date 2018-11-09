package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.math.Complex;
import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.editor.Keys;
import com.dacaspex.propertysheet.editor.PropertySheetCellEditor;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ComplexEditor extends PropertySheetCellEditor implements KeyListener {

    private Property<Complex> property;

    public ComplexEditor(Property<Complex> property, PropertySheet sheet) {
        super(property, sheet, new JTextField());

        this.property = property;

        super.getComponent().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent event) {

        if (Keys.ignoreKey(event.getKeyCode())) {
            return;
        }

        // Get value from editor component
        JTextField textField = (JTextField) super.getComponent();
        String value = textField.getText();

        // Validate input
        if (property.getValidator().validate(value)) {
            property.setValue(Complex.parseComplex(value));
            textField.setBackground(sheet.getOptions().getBackgroundColor());

            // Dispatch event to indicate something happened
            eventDispatcher.dispatchUpdateEvent(property);
        } else {
            textField.setBackground(sheet.getOptions().getInvalidColor());
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        return;
    }
}
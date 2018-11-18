package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.math.Complex;
import com.dacaspex.propertysheet.cell.AbstractCellComponent;
import com.dacaspex.propertysheet.util.Keys;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.KeyEvent;

public class ComplexCellComponent extends AbstractCellComponent {

    private ComplexProperty property;
    private JTextField textField;

    public ComplexCellComponent(ComplexProperty property) {
        this.property = property;
        this.textField = new JTextField();

        textField.addKeyListener(new ComplexCellComponent.KeyAdapter());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        textField.setText(property.getValue().toString());

        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        return property.getValue();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        return new JLabel(property.getValue().toString());
    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (Keys.ignoreKey(event.getKeyCode())) {
                return;
            }

            if (property.getValidator().validate(textField.getText())) {
                property.setValue(Complex.parseComplex(textField.getText()));
                textField.setBackground(options.getBackgroundColor());
                eventDispatcher.dispatchUpdateEvent(property);
            } else {
                textField.setBackground(options.getInvalidColor());
            }
        }
    }

}
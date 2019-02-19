package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.gui.colorband.ColorBandDisplay;
import com.dacaspex.propertysheet.cell.AbstractCellComponent;

import javax.swing.*;
import java.awt.*;

public class ColorBandCellComponent extends AbstractCellComponent {

    private ColorBandProperty property;
    private JButton delegate;

    public ColorBandCellComponent(ColorBandProperty property) {
        this.property = property;
        this.delegate = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (double x = 0; x < this.getWidth(); x++) {
                    double alpha = x / this.getWidth();
                    g.setColor(property.getValue().get(alpha));
                    g.fillRect((int) x, 0, 1, this.getHeight());
                }
            }
        };
        this.delegate.addActionListener(e -> {
            ColorBandDisplay panel = new ColorBandDisplay(property.getValue());
            panel.build();

            JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "Color band editor",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Ok", "Cancel"},
                    "Cancel"
            );
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        return delegate;
    }

    @Override
    public Object getCellEditorValue() {
        return property.getValue();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        return delegate;
    }
}

package com.dacaspex.algae.gui.settings;

import com.dacaspex.algae.gui.settings.util.RenderAction;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.property.ActionProperty;
import com.dacaspex.propertysheet.property.IntegerProperty;
import com.dacaspex.propertysheet.property.Property;
import com.dacaspex.propertysheet.validator.integer.IntegerValidatorFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ExportSettings extends JFrame {

    private PropertySheet propertySheet;

    private IntegerProperty widthProperty;
    private IntegerProperty heightProperty;
    private ActionProperty renderAction;

    public ExportSettings(
            FractalSettingsDisplay fractalSettings,
            ColorSchemeSettingsDisplay colorSchemeSettings,
            Scale scale
    ) {
        this.propertySheet = new PropertySheet(new PropertySheetOptions());
        this.widthProperty = new IntegerProperty(
                "Width",
                1920,
                new IntegerValidatorFactory()
                        .setLowerBound(0)
                        .build()
        );
        this.heightProperty = new IntegerProperty(
                "Height",
                1080,
                new IntegerValidatorFactory()
                        .setLowerBound(0)
                        .build()
        );
        this.renderAction = new ActionProperty(
                "Render",
                new RenderAction(
                        fractalSettings,
                        colorSchemeSettings,
                        scale,
                        widthProperty,
                        heightProperty,
                        new Renderer()
                )
        );

        addKeyListener(new KeyAdapter());
        propertySheet.addEventListener(new PropertySheetEventAdapter());
        build();
    }

    public void open() {
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }

    private void build() {
        setPreferredSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Build property sheet
        propertySheet.addProperty(widthProperty);
        propertySheet.addProperty(heightProperty);
        propertySheet.addProperty(renderAction);

        add(new JScrollPane(propertySheet));

        setVisible(false);
        pack();
    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    close();
                    break;

                default:
                    break;
            }
        }
    }

    private class PropertySheetEventAdapter extends com.dacaspex.propertysheet.event.PropertySheetEventAdapter {
        @Override
        public void onPropertyUpdated(Property property) {
        }
    }
}

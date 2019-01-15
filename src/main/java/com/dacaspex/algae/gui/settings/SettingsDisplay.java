package com.dacaspex.algae.gui.settings;

import com.dacaspex.algae.gui.settings.event.SettingUpdatedListener;
import com.dacaspex.algae.legacyGui.settings.util.ComplexCellComponent;
import com.dacaspex.algae.legacyGui.settings.util.ComplexProperty;
import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.event.PropertySheetEventAdapter;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsDisplay extends JFrame {

    private PropertySheet propertySheet;
    private SettingsProvider settingsProvider;

    private List<SettingUpdatedListener> listeners;

    public SettingsDisplay(SettingsProvider settingsProvider) {
        this.propertySheet = new PropertySheet(new PropertySheetOptions.Builder().build());
        this.settingsProvider = settingsProvider;
        this.listeners = new ArrayList<>();
    }

    public void addEventListener(SettingUpdatedListener listener) {
        listeners.add(listener);
    }

    public void build() {
        setPreferredSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        update();
        add(new JScrollPane(propertySheet));

        setVisible(false);
        pack();

        propertySheet.addEventListener(new PropertySheetEventListener());
    }

    public void open() {
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }

    private void update() {
        propertySheet.clear();

        for (Property p : settingsProvider.getProperties()) {
            if (p instanceof ComplexProperty) {
                propertySheet.addProperty(p, new ComplexCellComponent((ComplexProperty) p));
            } else {
                propertySheet.addProperty(p);
            }
        }

        propertySheet.repaint();
    }

    public void setSettingsProvider(SettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
    }

    /**
     * TODO: Key listeners for closing the display
     */

    private class PropertySheetEventListener extends PropertySheetEventAdapter {
        @Override
        public void onPropertyUpdated(Property property) {
            listeners.forEach(l -> l.onSettingUpdated(settingsProvider));
        }
    }
}

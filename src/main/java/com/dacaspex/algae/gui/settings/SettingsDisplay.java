package com.dacaspex.algae.gui.settings;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.event.PropertySheetEventAdapter;
import com.dacaspex.propertysheet.property.Property;

public class SettingsDisplay {

    private PropertySheet propertySheet;
    private SettingsProvider settingsProvider;

    public SettingsDisplay() {
        this.propertySheet = new PropertySheet(new PropertySheetOptions.Builder().build());
    }

    public void build() {
        propertySheet.addEventListener(new PropertySheetEventListener());
    }

    public void setSettingsProvider(SettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
    }

    private class PropertySheetEventListener extends PropertySheetEventAdapter {
        @Override
        public void onPropertyUpdated(Property property) {

        }
    }
}

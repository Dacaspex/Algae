package com.dacaspex.algae.gui.menu.event;

import com.dacaspex.algae.legacyGui.settings.colorScheme.ColorSchemeSettings;
import com.dacaspex.algae.legacyGui.settings.fractal.FractalSettings;

import java.util.ArrayList;
import java.util.List;

public class MenuEventDispatcher {

    private List<MenuEventListener> listeners;

    public MenuEventDispatcher() {
        this.listeners = new ArrayList<>();
    }

    public void addEventListener(MenuEventListener eventListener) {
        listeners.add(eventListener);
    }

    public void dispatchFractalSettingsSelected(FractalSettings fractalSettings) {
        listeners.forEach(l -> l.onFractalSettingsSelected(fractalSettings));
    }

    public void dispatchColorSchemeSettingsSelected(ColorSchemeSettings colorSchemeSettings) {
        listeners.forEach(l -> l.onColorSchemeSettingsSelected(colorSchemeSettings));
    }
}

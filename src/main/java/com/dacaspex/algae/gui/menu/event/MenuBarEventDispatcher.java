package com.dacaspex.algae.gui.menu.event;

import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettingsProvider;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;

import java.util.ArrayList;
import java.util.List;

public class MenuBarEventDispatcher {

    private List<MenuBarEventListener> listeners;

    public MenuBarEventDispatcher() {
        this.listeners = new ArrayList<>();
    }

    public void addEventListener(MenuBarEventListener eventListener) {
        listeners.add(eventListener);
    }

    public void dispatchFractalSettingsSelected(FractalSettingsProvider fractalSettings) {
        listeners.forEach(l -> l.onFractalSettingsSelected(fractalSettings));
    }

    public void dispatchColorSchemeSettingsSelected(ColorSchemeSettingsProvider colorSchemeSettings) {
        listeners.forEach(l -> l.onColorSchemeSettingsSelected(colorSchemeSettings));
    }

    public void dispatchFractalSettingsDisplayOpened() {
        listeners.forEach(MenuBarEventListener::onFractalSettingsOpened);
    }

    public void dispatchColorSchemeSettingsDisplayOpened() {
        listeners.forEach(MenuBarEventListener::onColorSchemeSettingsOpened);
    }

    public void dispatchExportDisplayOpened() {
        listeners.forEach(MenuBarEventListener::onExportDisplayOpened);
    }

    public void dispatchExportFractalSettingsDisplayOpened() {
        listeners.forEach(MenuBarEventListener::onExportFractalSettingsDisplayOpened);
    }

    public void dispatchExportColorSchemeSettingsDisplayOpened() {
        listeners.forEach(MenuBarEventListener::onExportColorSchemeSettingsDisplayOpened);
    }

    public void dispatchImportFractalSettingsDisplayOpened() {
        listeners.forEach(MenuBarEventListener::onImportFractalSettingsDisplayOpened);
    }

    public void dispatchImportColorSchemeSettingsDisplayOpened() {
        listeners.forEach(MenuBarEventListener::onImportColorSchemeSettingsDisplayOpened);
    }

    public void dispatchExit() {
        listeners.forEach(MenuBarEventListener::onExit);
    }
}

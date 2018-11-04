package com.dacaspex.algae.gui.settings;

import com.dacaspex.algae.gui.Display;
import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettings;
import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.event.PropertySheetEventListener;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorSchemeSettingsDisplay extends JFrame implements KeyListener, PropertySheetEventListener {

    private PropertySheet propertySheet;
    private Display display;
    private ColorSchemeSettings settings;

    public ColorSchemeSettingsDisplay(Display display, ColorSchemeSettings settings) {
        this.display = display;
        this.settings = settings;
        this.propertySheet = new PropertySheet(new PropertySheetOptions());

        addKeyListener(this);
        propertySheet.addEventListener(this);
        build();
    }

    public ColorSchemeSettings getSettings() {
        return settings;
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

        for (Property p : settings.getProperties()) {
            propertySheet.addProperty(p);
        }

        add(new JScrollPane(propertySheet));

        setVisible(false);
        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void onPropertyUpdated(Property property) {
        display.render();
    }

    @Override
    public void onPropertyAdded(Property property) {

    }
}

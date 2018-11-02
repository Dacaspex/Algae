package com.dacaspex.algae.gui;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.PropertySheetOptions;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorSchemeSettingsDisplay extends JFrame implements KeyListener {

    private PropertySheet propertySheet;

    public ColorSchemeSettingsDisplay() {
        this.propertySheet = new PropertySheet(new PropertySheetOptions());

        addKeyListener(this);
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
}

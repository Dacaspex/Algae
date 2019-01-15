package com.dacaspex.algae.gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusBar extends JPanel {

    public void build() {
        setLayout(new FlowLayout());
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        add(new JLabel("Status"));
    }
}

package com.dacaspex.algae.gui.status;

import com.dacaspex.algae.renderer.RenderStage;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusBar extends JPanel {

    private final JLabel statusLabel;
    private final JProgressBar progressBar;

    public StatusBar() {
        this.statusLabel = new JLabel();
        this.progressBar = new JProgressBar();
    }

    public void build() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        // Build label
        statusLabel.setText("Status");

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setString("Awaiting render...");
        progressBar.setStringPainted(true);

        add(statusLabel);
        add(progressBar);
    }

    public void updateRenderStage(RenderStage renderStage) {
        progressBar.setString(renderStage.toString());
    }

    public void updateProgress(int progress) {
        progressBar.setValue(progress);
    }
}

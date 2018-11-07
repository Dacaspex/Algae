package com.dacaspex.algae.util;

import com.dacaspex.algae.gui.Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ResizeDelayTimer implements ActionListener {

    private Timer timer;
    private Display display;

    public ResizeDelayTimer(int delay, Display display) {
        this.display = display;
        this.timer = new Timer(delay, this);
    }

    /**
     * Restarts the timer. This should be called when the screen is resized. It
     * acts as a start method as well.
     */
    public void restart() {
        timer.restart();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        timer.stop();
        display.render();
    }
}
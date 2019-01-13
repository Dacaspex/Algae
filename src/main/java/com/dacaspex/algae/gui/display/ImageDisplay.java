package com.dacaspex.algae.gui.display;

import com.dacaspex.algae.gui.display.event.ImageDisplayEventDispatcher;
import com.dacaspex.algae.gui.display.event.ImageDisplayEventListener;
import com.dacaspex.algae.math.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImageDisplay extends JPanel {

    private BufferedImage image;
    private ImageDisplayEventDispatcher eventDispatcher;

    public void updateImage(BufferedImage image) {
        this.image = image;
        this.eventDispatcher = new ImageDisplayEventDispatcher();

        repaint();
    }

    public void build() {
        addMouseListener(new MouseListener());
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        if (image == null) {
            return;
        }

        graphics.drawImage(image, 0, 0, null);
    }

    public void addEventListener(ImageDisplayEventListener listener) {
        eventDispatcher.addEventListener(listener);
    }

    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    eventDispatcher.dispatchZoomInEvent(
                            new Vector2d(e.getPoint().x, e.getPoint().y)
                    );
                    break;

                case MouseEvent.BUTTON3:
                    eventDispatcher.dispatchZoomOutEvent(
                            new Vector2d(e.getPoint().x, e.getPoint().y)
                    );
                    break;

                case MouseEvent.BUTTON2:
                    eventDispatcher.dispatchTranslateEvent(
                            new Vector2d(e.getPoint().x, e.getPoint().y)
                    );
                    break;

                default:
                    break;
            }
        }
    }
}

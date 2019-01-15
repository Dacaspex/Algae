package com.dacaspex.algae.gui.display.event;

import com.dacaspex.algae.math.Vector2d;

public interface ImageDisplayEventListener {

    public void onZoomIn(Vector2d point);

    public void onZoomOut(Vector2d point);

    public void onTranslate(Vector2d point);
}

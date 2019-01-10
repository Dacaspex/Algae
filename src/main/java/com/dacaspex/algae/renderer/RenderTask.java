package com.dacaspex.algae.renderer;

public class RenderTask {

    public final int xStart;
    public final int yStart;
    public final int height;
    public final int width;

    public RenderTask(int xStart, int yStart, int width, int height) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
    }
}

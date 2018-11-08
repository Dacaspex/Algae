package com.dacaspex.algae.render;

public class RenderJob {

    public int xStart, yStart;
    public int width, height;

    public RenderJob(int xStart, int yStart, int width, int height) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
    }
}

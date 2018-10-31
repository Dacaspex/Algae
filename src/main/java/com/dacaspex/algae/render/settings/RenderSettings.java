package com.dacaspex.algae.render.settings;

public class RenderSettings {

    public int width;
    public int height;
    public int outputWidth;
    public int outputHeight;

    public RenderSettings(int width, int height, int outputWidth, int outputHeight) {
        this.width = width;
        this.height = height;
        this.outputWidth = outputWidth;
        this.outputHeight = outputHeight;
    }

    public RenderSettings(int outputWidth, int outputHeight) {
        this.width = outputWidth;
        this.height = outputHeight;
        this.outputWidth = outputWidth;
        this.outputHeight = outputHeight;
    }
}

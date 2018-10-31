package com.dacaspex.algae.render.settings;

public class RenderSettings {

    public int width;
    public int height;
    public double preProcessorQuality;
    public double renderQuality;

    public RenderSettings(int width, int height, double preProcessorQuality, double renderQuality) {
        this.width = width;
        this.height = height;
        this.preProcessorQuality = preProcessorQuality;
        this.renderQuality = renderQuality;
    }

    public RenderSettings(int width, int height) {
        this(width, height, 0.5, 1);
    }

    public int getPreProcessorWidth() {
        return (int) (preProcessorQuality * width);
    }

    public int getPreProcessorHeight() {
        return (int) (preProcessorQuality * height);
    }

    public int getRenderWidth() {
        return (int) (renderQuality * width);
    }

    public int getRenderHeight() {
        return (int) (renderQuality * height);
    }
}

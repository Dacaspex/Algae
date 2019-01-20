package com.dacaspex.algae.renderer;

public enum RenderStage {
    BUILDING_TASKS("Building tasks"),
    PRE_PROCESSING("Pre-processing"),
    RENDERING("Rendering"),
    STITCHING("Stitching"),
    COMPLETED("Completed");

    private String status;

    private RenderStage(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}

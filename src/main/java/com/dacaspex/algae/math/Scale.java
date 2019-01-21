package com.dacaspex.algae.math;

public class Scale {

    private Vector2d center;
    private double zoomLevel;
    private double xDensity, yDensity;

    public Scale(Vector2d center, double zoomLevel, double xDensity, double yDensity) {
        this.center = center;
        this.zoomLevel = zoomLevel;
        this.xDensity = xDensity;
        this.yDensity = yDensity;
    }

    public Scale(Vector2d center, double zoomLevel) {
        this(center, zoomLevel, 0.002, 0.002);
    }

    public Scale(Vector2d center) {
        this(center, 1, 0.002, 0.002);
    }

    public Scale() {
        this(new Vector2d(), 1, 0.002, 0.002);
    }

    public void setCenter(Vector2d center) {
        this.center = center;
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(double zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public Vector2d getScreenPointInScale(Vector2d screenPoint, int screenWidth, int screenHeight) {
        // Calculate x point
        double xScreen = screenPoint.x - (screenWidth / 2.0);
        double x = center.x + xDensity * (1 / zoomLevel) * xScreen;

        // Calculate x point
        double yScreen = screenPoint.y - (screenHeight / 2.0);
        double y = center.y - yDensity * (1 / zoomLevel) * yScreen;

        return new Vector2d(x, y);
    }

    public Vector2d[][] getPoints(int width, int height) {
        Vector2d[][] points = new Vector2d[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Map x to [-0.5width, 0.5width], vice versa for y
                int xMapped = x - (width / 2);
                int yMapped = y - (height / 2);

                // Calculate point in scale
                points[x][y] = new Vector2d(
                        center.x + xDensity * (1 / zoomLevel) * xMapped,
                        center.y - yDensity * (1 / zoomLevel) * yMapped
                );
            }
        }

        return points;
    }
}

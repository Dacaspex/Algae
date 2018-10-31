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

    public Scale(Vector2d center, double zoomLevel, double density) {
        this(center, zoomLevel, density, density);
    }

    public Scale(Vector2d center) {
        this(center, 1, 0.002, 0.002);
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

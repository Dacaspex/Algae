package com.dacaspex.algae.util.math;

public class Scale {

    /**
     * Center of the scale
     */
    private Vector2d center;
    /**
     * Zoom level. 1 / zoomLevel determines the amount of pixels in either the width or height
     * direction, depending on which is bigger.
     */
    private double zoomLevel;

    public Scale(Vector2d center, double zoomLevel) {
        this.center = center;
        this.zoomLevel = zoomLevel;
    }

    public Scale() {
        this(new Vector2d(), 1);
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    /**
     * Maps a point on the screen to a point in the scale
     *
     * @param point  Point on the screen (0 <= point.x <= width, 0 <= point.y <= height)
     * @param width  Width of the screen
     * @param height Height of the screen
     * @return Mapped point in the scale
     */
    public Vector2d getScreenPointInScale(Vector2d point, int width, int height) {
        double stepSize = Math.max(1.0f / (width * zoomLevel), 1.0f / (height * zoomLevel));
        double xMin = center.x - 0.5f * width * stepSize;
        double yMin = center.y - 0.5f * height * stepSize;

        double x = xMin + point.x * stepSize;
        double y = yMin + point.y * stepSize;

        return new Vector2d(x, y);
    }

    /**
     * Generates a list of points such that each coordinate in the map p[width][height] is mapped to
     * a point in the scale. The aspect ratios are maintained.
     *
     * @param width  Width of the map
     * @param height Height of the map
     * @return 2-dimensional mapping between integer map m[width][height] and the scale
     */
    public Vector2d[][] getPoints(int width, int height) {
        Vector2d[][] points = new Vector2d[width][height];

        // step_size = max(step_size_x, step_size_y)
        // step_size_x = (1/z) / width ;
        double stepSize = Math.max(1.0f / (width * zoomLevel), 1.0f / (height * zoomLevel));
        double xMin = center.x - 0.5f * width * stepSize;
        double yMin = center.y - 0.5f * height * stepSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double x = xMin + i * stepSize;
                double y = yMin + j * stepSize;

                points[i][j] = new Vector2d(x, y);
            }
        }

        return points;
    }
}

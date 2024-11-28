package engine.render;

import engine.math.Vector3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShapeRenderer {
    /**
     * Draw a 2D line on the canvas.
     */
    public void drawLine(GraphicsContext gc, Vector3 start, Vector3 end, int width, int height) {
        int x1 = (int) ((start.x + 1) * width / 2);
        int y1 = (int) ((1 - start.y) * height / 2);
        int x2 = (int) ((end.x + 1) * width / 2);
        int y2 = (int) ((1 - end.y) * height / 2);

        gc.setStroke(Color.WHITE);
        gc.strokeLine(x1, y1, x2, y2);
    }

    /**
     * Draw a 2D circle on the canvas.
     */
    public void drawCircle(GraphicsContext gc, Vector3 center, double radius, int width, int height) {
        double x = (center.x + 1) * width / 2 - radius * width / 2;
        double y = (1 - center.y) * height / 2 - radius * height / 2;
        double diameter = radius * width;

        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, diameter, diameter);
    }

    /**
     * Draw a filled triangle on the canvas.
     */
    public void drawTriangle(GraphicsContext gc, Vector3 v1, Vector3 v2, Vector3 v3, int width, int height) {
        int x1 = (int) ((v1.x + 1) * width / 2);
        int y1 = (int) ((1 - v1.y) * height / 2);
        int x2 = (int) ((v2.x + 1) * width / 2);
        int y2 = (int) ((1 - v2.y) * height / 2);
        int x3 = (int) ((v3.x + 1) * width / 2);
        int y3 = (int) ((1 - v3.y) * height / 2);

        gc.setFill(Color.LIGHTGRAY);
        gc.fillPolygon(
                new double[]{x1, x2, x3},
                new double[]{y1, y2, y3},
                3
        );
    }
}

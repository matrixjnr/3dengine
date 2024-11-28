package engine.render;

import engine.math.Vector3;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ShapeRendererTest {

    @Test
    void drawLine() {
        // Mock GraphicsContext
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = spy(canvas.getGraphicsContext2D());

        // Define start and end points for the line
        Vector3 start = new Vector3(-1, -1, 0);
        Vector3 end = new Vector3(1, 1, 0);

        // Create the renderer and call drawLine
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.drawLine(gc, start, end, 800, 600);

        // Verify that strokeLine was called with the correct screen-space coordinates
        verify(gc, times(1)).strokeLine(eq(0.0), eq(600.0), eq(800.0), eq(0.0));
    }

    @Test
    void drawCircle() {
        // Mock GraphicsContext
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = spy(canvas.getGraphicsContext2D());

        // Define the circle's center and radius
        Vector3 center = new Vector3(0, 0, 0); // Center at the origin
        double radius = 0.5; // Circle radius (normalized space)

        // Create the renderer and call drawCircle
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.drawCircle(gc, center, radius, 800, 600);

        // Verify that fillOval was called with the correct screen-space coordinates and dimensions
        double expectedX = (center.x + 1) * 800 / 2 - radius * 800 / 2;
        double expectedY = (1 - center.y) * 600 / 2 - radius * 600 / 2;
        double expectedDiameter = radius * 800; // Radius scaled to width

        verify(gc, times(1)).fillOval(eq(expectedX), eq(expectedY), eq(expectedDiameter), eq(expectedDiameter));
    }

    @Test
    void drawTriangle() {
        // Mock GraphicsContext
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = spy(canvas.getGraphicsContext2D());

        // Define the triangle's vertices
        Vector3 v1 = new Vector3(-0.5, -0.5, 0);
        Vector3 v2 = new Vector3(0.5, -0.5, 0);
        Vector3 v3 = new Vector3(0, 0.5, 0);

        // Create the renderer and call drawTriangle
        ShapeRenderer renderer = new ShapeRenderer();
        renderer.drawTriangle(gc, v1, v2, v3, 800, 600);

        // Verify that fillPolygon was called with the correct screen-space coordinates
        verify(gc, times(1)).fillPolygon(
                Mockito.any(double[].class), // X-coordinates array
                Mockito.any(double[].class), // Y-coordinates array
                eq(3) // Number of vertices
        );
    }
}

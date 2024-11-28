package engine.render;

import engine.math.Matrix4;
import engine.math.Vector3;
import engine.scene.Mesh;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class RendererTest {

    @Test
    void render3D() {
        // Create a mock GraphicsContext to verify drawing operations
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = spy(canvas.getGraphicsContext2D());

        // Define a simple mesh (a triangle)
        Mesh mesh = new Mesh(
                new Vector3[]{
                        new Vector3(0, 0, 1),
                        new Vector3(1, 0, 1),
                        new Vector3(0, 1, 1),
                },
                new int[][]{
                        {0, 1, 2} // Single triangular face
                }
        );

        // Transformation and projection matrices
        Matrix4 transformation = Matrix4.identity(); // No transformation
        Matrix4 projection = Matrix4.identity(); // No projection for simplicity

        // Call the render3D method
        Renderer renderer = new Renderer();
        renderer.render3D(gc, mesh, transformation, projection, 800, 600);

        // Verify the drawing calls
        verify(gc, times(1)).strokePolygon(
                Mockito.any(double[].class), // x-coordinates
                Mockito.any(double[].class), // y-coordinates
                eq(3) // Number of vertices in the triangle
        );
    }
}

package engine.scene;

import engine.math.Matrix4;
import engine.math.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {

    @Test
    void getPosition() {
        // Create a Camera instance
        Vector3 position = new Vector3(1, 2, 3);
        Camera camera = new Camera(position, 90, 16.0 / 9.0, 0.1, 100.0);

        // Verify the position
        assertEquals(position, camera.getPosition(), "Camera position should match the provided vector");
    }

    @Test
    void getProjectionMatrix() {
        // Create a Camera instance
        double fov = 90;
        double aspectRatio = 16.0 / 9.0;
        double near = 0.1;
        double far = 100.0;
        Camera camera = new Camera(new Vector3(0, 0, 0), fov, aspectRatio, near, far);

        // Get the projection matrix
        Matrix4 projectionMatrix = camera.getProjectionMatrix();

        // Calculate expected values for the projection matrix
        double scale = 1.0 / Math.tan(Math.toRadians(fov / 2));
        double expectedA = scale / aspectRatio;
        double expectedB = scale;
        double expectedC = -(far + near) / (far - near);
        double expectedD = -2 * far * near / (far - near);

        // Verify the projection matrix values
        assertEquals(expectedA, projectionMatrix.values[0][0], 1e-9, "Projection matrix: scale / aspectRatio should match");
        assertEquals(expectedB, projectionMatrix.values[1][1], 1e-9, "Projection matrix: scale should match");
        assertEquals(expectedC, projectionMatrix.values[2][2], 1e-9, "Projection matrix: -(far + near) / (far - near) should match");
        assertEquals(expectedD, projectionMatrix.values[2][3], 1e-9, "Projection matrix: -2 * far * near / (far - near) should match");
        assertEquals(-1.0, projectionMatrix.values[3][2], 1e-9, "Projection matrix: -1 should be set at [3][2]");
    }
}

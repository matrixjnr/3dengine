package engine.scene;

import engine.math.Matrix4;
import engine.math.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeshTest {

    @Test
    void transform() {
        // Define a simple mesh with two vertices
        Vector3[] vertices = {
                new Vector3(1, 1, 1),
                new Vector3(2, 2, 2)
        };
        int[][] faces = {}; // Faces are irrelevant for this test
        Mesh mesh = new Mesh(vertices, faces);

        // Define a transformation matrix (translation by (1, 1, 1))
        Matrix4 translationMatrix = Matrix4.translation(1, 1, 1);

        // Apply the transformation
        Vector3[] transformedVertices = mesh.transform(translationMatrix);

        // Expected transformed vertices
        Vector3[] expectedVertices = {
                new Vector3(2, 2, 2),
                new Vector3(3, 3, 3)
        };

        // Verify each transformed vertex
        for (int i = 0; i < vertices.length; i++) {
            assertEquals(expectedVertices[i].x, transformedVertices[i].x, 1e-9, "X component should match");
            assertEquals(expectedVertices[i].y, transformedVertices[i].y, 1e-9, "Y component should match");
            assertEquals(expectedVertices[i].z, transformedVertices[i].z, 1e-9, "Z component should match");
        }
    }
}

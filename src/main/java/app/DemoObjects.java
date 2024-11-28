package app;

import engine.math.Vector3;
import engine.scene.Mesh;

public class DemoObjects {

    /**
     * Creates a simple 3D cube.
     * @return Mesh representing a cube.
     */
    public static Mesh createCube() {
        Vector3[] vertices = {
                new Vector3(-0.5, -0.5, -0.5), // 0
                new Vector3(0.5, -0.5, -0.5),  // 1
                new Vector3(0.5, 0.5, -0.5),   // 2
                new Vector3(-0.5, 0.5, -0.5),  // 3
                new Vector3(-0.5, -0.5, 0.5),  // 4
                new Vector3(0.5, -0.5, 0.5),   // 5
                new Vector3(0.5, 0.5, 0.5),    // 6
                new Vector3(-0.5, 0.5, 0.5)    // 7
        };

        int[][] faces = {
                {0, 1, 2, 3}, // Front face
                {4, 5, 6, 7}, // Back face
                {0, 1, 5, 4}, // Bottom face
                {2, 3, 7, 6}, // Top face
                {0, 3, 7, 4}, // Left face
                {1, 2, 6, 5}  // Right face
        };

        return new Mesh(vertices, faces);
    }

    /**
     * Creates a simple 2D square.
     * @return Mesh representing a square (as a flat 3D object).
     */
    public static Mesh createSquare() {
        Vector3[] vertices = {
                new Vector3(-0.5, -0.5, 0), // Bottom-left
                new Vector3(0.5, -0.5, 0),  // Bottom-right
                new Vector3(0.5, 0.5, 0),   // Top-right
                new Vector3(-0.5, 0.5, 0)   // Top-left
        };

        int[][] faces = {
                {0, 1, 2, 3} // Single square face
        };

        return new Mesh(vertices, faces);
    }

    /**
     * Creates a simple 3D pyramid.
     * @return Mesh representing a pyramid.
     */
    public static Mesh createPyramid() {
        Vector3[] vertices = {
                new Vector3(0, 0.5, 0),   // Top (apex)
                new Vector3(-0.5, -0.5, -0.5), // Bottom-left front
                new Vector3(0.5, -0.5, -0.5),  // Bottom-right front
                new Vector3(0.5, -0.5, 0.5),   // Bottom-right back
                new Vector3(-0.5, -0.5, 0.5)   // Bottom-left back
        };

        int[][] faces = {
                {0, 1, 2}, // Front face
                {0, 2, 3}, // Right face
                {0, 3, 4}, // Back face
                {0, 4, 1}, // Left face
                {1, 2, 3, 4} // Bottom face
        };

        return new Mesh(vertices, faces);
    }

    /**
     * Creates a 2D circle as a flat 3D object.
     * @param segments Number of segments to approximate the circle.
     * @return Mesh representing a circle.
     */
    public static Mesh createCircle(int segments) {
        Vector3[] vertices = new Vector3[segments + 1];
        int[][] faces = new int[segments][3];

        // Center of the circle
        vertices[0] = new Vector3(0, 0, 0);

        // Generate the circle vertices
        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            vertices[i + 1] = new Vector3(Math.cos(angle) * 0.5, Math.sin(angle) * 0.5, 0);

            // Create faces
            if (i > 0) {
                faces[i - 1] = new int[]{0, i, i + 1};
            }
        }

        // Close the circle
        faces[segments - 1] = new int[]{0, segments, 1};

        return new Mesh(vertices, faces);
    }

    /**
     * Creates a 3D sphere.
     * @param stacks Number of vertical divisions.
     * @param slices Number of horizontal divisions.
     * @return Mesh representing a sphere.
     */
    public static Mesh createSphere(int stacks, int slices) {
        Vector3[] vertices = new Vector3[(stacks + 1) * (slices + 1)];
        int[][] faces = new int[stacks * slices * 2][3];

        int index = 0;

        // Generate vertices
        for (int stack = 0; stack <= stacks; stack++) {
            double phi = Math.PI * stack / stacks;
            for (int slice = 0; slice <= slices; slice++) {
                double theta = 2 * Math.PI * slice / slices;
                double x = Math.sin(phi) * Math.cos(theta) * 0.5;
                double y = Math.cos(phi) * 0.5;
                double z = Math.sin(phi) * Math.sin(theta) * 0.5;

                vertices[index++] = new Vector3(x, y, z);
            }
        }

        // Generate faces
        index = 0;
        for (int stack = 0; stack < stacks; stack++) {
            for (int slice = 0; slice < slices; slice++) {
                int first = stack * (slices + 1) + slice;
                int second = first + slices + 1;

                faces[index++] = new int[]{first, second, first + 1};
                faces[index++] = new int[]{second, second + 1, first + 1};
            }
        }

        return new Mesh(vertices, faces);
    }
}

package engine.scene;

import engine.math.Vector3;

public class Mesh {
    public Vector3[] vertices;
    public int[][] faces; // Indices defining which vertices form a face (e.g., triangles or quads)

    public Mesh(Vector3[] vertices, int[][] faces) {
        this.vertices = vertices;
        this.faces = faces;
    }

    /**
     * Apply transformations to the mesh and return transformed vertices.
     */
    public Vector3[] transform(engine.math.Matrix4 transformationMatrix) {
        Vector3[] transformed = new Vector3[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            transformed[i] = transformationMatrix.multiplyVector(vertices[i]);
        }
        return transformed;
    }
}


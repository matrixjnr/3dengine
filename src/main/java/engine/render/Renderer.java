package engine.render;

import engine.math.Matrix4;
import engine.math.Vector3;
import engine.scene.Mesh;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {

    public void render3D(GraphicsContext gc, Mesh mesh, Matrix4 transform, Matrix4 projection, int canvasWidth, int canvasHeight) {
        Vector3[] transformedVertices = mesh.transform(transform);
        double[] screenX = new double[transformedVertices.length];
        double[] screenY = new double[transformedVertices.length];

        for (int i = 0; i < transformedVertices.length; i++) {
            Vector3 v = transformedVertices[i];
            Vector3 projected = projection.multiplyVector(v);

            // Map 3D points to 2D canvas space
            screenX[i] = (projected.x + 1) * canvasWidth / 2;
            screenY[i] = (1 - projected.y) * canvasHeight / 2;
        }

        for (int[] face : mesh.faces) {
            double[] faceX = new double[face.length];
            double[] faceY = new double[face.length];
            for (int j = 0; j < face.length; j++) {
                faceX[j] = screenX[face[j]];
                faceY[j] = screenY[face[j]];
            }

            // Fill the face with a transparent color
            gc.setFill(Color.color(Math.random(), Math.random(), Math.random(), 0.5)); // Random colors for each face
            gc.fillPolygon(faceX, faceY, face.length);

            // Outline the face with a solid stroke color
            gc.setStroke(Color.WHITE);
            gc.strokePolygon(faceX, faceY, face.length);
        }
    }
}

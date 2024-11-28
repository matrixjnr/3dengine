package engine.scene;

import engine.math.Matrix4;
import engine.math.Vector3;

public class Camera {
    private final Vector3 position;
    private final double fov;
    private final double aspectRatio;
    private final double near;
    private final double far;

    public Camera(Vector3 position, double fov, double aspectRatio, double near, double far) {
        this.position = position;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        this.near = near;
        this.far = far;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Matrix4 getProjectionMatrix() {
        double scale = 1.0 / Math.tan(Math.toRadians(fov / 2));
        Matrix4 projection = new Matrix4();
        projection.values[0][0] = scale / aspectRatio;
        projection.values[1][1] = scale;
        projection.values[2][2] = -(far + near) / (far - near);
        projection.values[2][3] = -2 * far * near / (far - near);
        projection.values[3][2] = -1;
        projection.values[3][3] = 0;
        return projection;
    }
}

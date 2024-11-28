package engine.math;

/**
 * Represents a 4x4 matrix.
 */
public class Matrix4 {
    public double[][] values;

    /**
     * Constructs a new 4x4 matrix with all values set to 0.
     */
    public Matrix4() {
        values = new double[4][4];
    }

    /**
     * Get a new 4x4 identity matrix.
     */
    public static Matrix4 identity() {
        Matrix4 matrix = new Matrix4();
        for (int i = 0; i < 4; i++) {
            matrix.values[i][i] = 1.0;
        }
        return matrix;
    }

    /**
     * Translates a 4x4 matrix by the given values.
     *
     * @param tx the translation in the x direction
     *           (positive values move right, negative values move left)
     * @param ty the translation in the y direction
     *           (positive values move up, negative values move down)
     * @param tz the translation in the z direction
     *           (positive values move forward, negative values move backward)
     *
     * @return the translated matrix
     */
    public static Matrix4 translation(double tx, double ty, double tz) {
        Matrix4 matrix = identity();
        matrix.values[0][3] = tx;
        matrix.values[1][3] = ty;
        matrix.values[2][3] = tz;
        return matrix;
    }

    /**
     * Scales a 4x4 matrix by the given values.
     *
     * @param sx the scaling in the x direction
     *           (1 means no scaling, 2 means double the size, 0.5 means half the size)
     * @param sy the scaling in the y direction
     *           (1 means no scaling, 2 means double the size, 0.5 means half the size)
     * @param sz the scaling in the z direction
     *           (1 means no scaling, 2 means double the size, 0.5 means half the size)
     *
     * @return the scaled matrix
     */
    public static Matrix4 scaling(double sx, double sy, double sz) {
        Matrix4 matrix = identity();
        matrix.values[0][0] = sx;
        matrix.values[1][1] = sy;
        matrix.values[2][2] = sz;
        return matrix;
    }

    /**
     * Rotates a 4x4 matrix around the x-axis by the given angle.
     *
     * @param angle the angle of rotation in radians
     *              (positive values rotate counter-clockwise, negative values rotate clockwise)
     *
     * @return the rotated matrix
     */
    public static Matrix4 rotationX(double angle) {
        Matrix4 matrix = identity();
        matrix.values[1][1] = Math.cos(angle);
        matrix.values[1][2] = -Math.sin(angle);
        matrix.values[2][1] = Math.sin(angle);
        matrix.values[2][2] = Math.cos(angle);
        return matrix;
    }

    /**
     * Rotates a 4x4 matrix around the y-axis by the given angle.
     *
     * @param angle the angle of rotation in radians
     *              (positive values rotate counter-clockwise, negative values rotate clockwise)
     *
     * @return the rotated matrix
     */
    public static Matrix4 rotationY(double angle) {
        Matrix4 matrix = identity();
        matrix.values[0][0] = Math.cos(angle);
        matrix.values[0][2] = Math.sin(angle);
        matrix.values[2][0] = -Math.sin(angle);
        matrix.values[2][2] = Math.cos(angle);
        return matrix;
    }

    /**
     * Rotates a 4x4 matrix around the z-axis by the given angle.
     *
     * @param angle the angle of rotation in radians
     *              (positive values rotate counter-clockwise, negative values rotate clockwise)
     *
     * @return the rotated matrix
     */
    public static Matrix4 rotationZ(double angle) {
        Matrix4 matrix = identity();
        matrix.values[0][0] = Math.cos(angle);
        matrix.values[0][1] = -Math.sin(angle);
        matrix.values[1][0] = Math.sin(angle);
        matrix.values[1][1] = Math.cos(angle);
        return matrix;
    }

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param other the matrix to multiply by
     *
     * @return the result of the multiplication
     */
    public Matrix4 multiply(Matrix4 other) {
        Matrix4 result = new Matrix4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result.values[i][j] += this.values[i][k] * other.values[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Transforms a Vector3 using this Matrix4 and returns the resulting Vector3.
     * Assumes the Vector3 is treated as a 4D vector with w = 1.0.
     */
    public Vector3 multiplyVector(Vector3 vector) {
        double[] result = new double[4];
        double[] input = {vector.x, vector.y, vector.z, 1.0}; // Homogeneous coordinates

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i] += this.values[i][j] * input[j];
            }
        }

        // Return the transformed vector, ignoring w for simplicity
        return new Vector3(result[0], result[1], result[2]);
    }
}


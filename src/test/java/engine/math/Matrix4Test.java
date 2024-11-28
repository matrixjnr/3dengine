package engine.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix4Test {

    @Test
    void identity() {
        Matrix4 identity = Matrix4.identity();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    assertEquals(1.0, identity.values[i][j], 1e-9, "Diagonal elements should be 1");
                } else {
                    assertEquals(0.0, identity.values[i][j], 1e-9, "Off-diagonal elements should be 0");
                }
            }
        }
    }

    @Test
    void translation() {
        Matrix4 translation = Matrix4.translation(2, 3, 4);
        assertEquals(2.0, translation.values[0][3], 1e-9, "Translation X should be 2");
        assertEquals(3.0, translation.values[1][3], 1e-9, "Translation Y should be 3");
        assertEquals(4.0, translation.values[2][3], 1e-9, "Translation Z should be 4");
        assertEquals(1.0, translation.values[3][3], 1e-9, "Last element should be 1");
    }

    @Test
    void scaling() {
        Matrix4 scaling = Matrix4.scaling(2, 3, 4);
        assertEquals(2.0, scaling.values[0][0], 1e-9, "Scaling X should be 2");
        assertEquals(3.0, scaling.values[1][1], 1e-9, "Scaling Y should be 3");
        assertEquals(4.0, scaling.values[2][2], 1e-9, "Scaling Z should be 4");
        assertEquals(1.0, scaling.values[3][3], 1e-9, "Last element should be 1");
    }

    @Test
    void rotationX() {
        double angle = Math.PI / 2; // 90 degrees
        Matrix4 rotation = Matrix4.rotationX(angle);

        assertEquals(1.0, rotation.values[0][0], 1e-9, "RotationX: X axis should not change");
        assertEquals(0.0, rotation.values[0][1], 1e-9, "RotationX: Should not affect X axis");

        assertEquals(0.0, rotation.values[1][0], 1e-9, "RotationX: Should not affect other axes");
        assertEquals(0.0, rotation.values[2][0], 1e-9, "RotationX: Should not affect other axes");

        assertEquals(Math.cos(angle), rotation.values[1][1], 1e-9, "RotationX: Y-Y cosine should match");
        assertEquals(-Math.sin(angle), rotation.values[1][2], 1e-9, "RotationX: Y-Z sine should match");

        assertEquals(Math.sin(angle), rotation.values[2][1], 1e-9, "RotationX: Z-Y sine should match");
        assertEquals(Math.cos(angle), rotation.values[2][2], 1e-9, "RotationX: Z-Z cosine should match");
    }

    @Test
    void rotationY() {
        double angle = Math.PI / 4; // 45 degrees
        Matrix4 rotation = Matrix4.rotationY(angle);

        assertEquals(Math.cos(angle), rotation.values[0][0], 1e-9, "RotationY: Cosine on X-X should match");
        assertEquals(Math.sin(angle), rotation.values[0][2], 1e-9, "RotationY: Sine on X-Z should match");

        assertEquals(-Math.sin(angle), rotation.values[2][0], 1e-9, "RotationY: Negative sine on Z-X should match");
        assertEquals(Math.cos(angle), rotation.values[2][2], 1e-9, "RotationY: Cosine on Z-Z should match");

        assertEquals(1.0, rotation.values[1][1], 1e-9, "RotationY: Y axis should not change");
    }

    @Test
    void rotationZ() {
        double angle = Math.PI / 6; // 30 degrees
        Matrix4 rotation = Matrix4.rotationZ(angle);

        assertEquals(Math.cos(angle), rotation.values[0][0], 1e-9, "RotationZ: Cosine on X-X should match");
        assertEquals(-Math.sin(angle), rotation.values[0][1], 1e-9, "RotationZ: Negative sine on X-Y should match");

        assertEquals(Math.sin(angle), rotation.values[1][0], 1e-9, "RotationZ: Sine on Y-X should match");
        assertEquals(Math.cos(angle), rotation.values[1][1], 1e-9, "RotationZ: Cosine on Y-Y should match");

        assertEquals(1.0, rotation.values[2][2], 1e-9, "RotationZ: Z axis should not change");
    }

    @Test
    void multiply() {
        Matrix4 a = Matrix4.translation(1, 2, 3);
        Matrix4 b = Matrix4.scaling(2, 2, 2);
        Matrix4 result = a.multiply(b);

        assertEquals(2.0, result.values[0][0], 1e-9, "Multiply: Scaling X should match");
        assertEquals(2.0, result.values[1][1], 1e-9, "Multiply: Scaling Y should match");
        assertEquals(2.0, result.values[2][2], 1e-9, "Multiply: Scaling Z should match");

        assertEquals(1.0, result.values[0][3], 1e-9, "Multiply: Translation X should match");
        assertEquals(2.0, result.values[1][3], 1e-9, "Multiply: Translation Y should match");
        assertEquals(3.0, result.values[2][3], 1e-9, "Multiply: Translation Z should match");
    }

    @Test
    void multiplyVector() {
        Matrix4 translation = Matrix4.translation(2, 3, 4);
        Vector3 vector = new Vector3(1, 1, 1);

        Vector3 transformed = translation.multiplyVector(vector);

        assertEquals(3.0, transformed.x, 1e-9, "MultiplyVector: X should be translated");
        assertEquals(4.0, transformed.y, 1e-9, "MultiplyVector: Y should be translated");
        assertEquals(5.0, transformed.z, 1e-9, "MultiplyVector: Z should be translated");
    }
}

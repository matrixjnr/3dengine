package engine.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    @Test
    void add() {
        Vector3 v1 = new Vector3(1, 2, 3);
        Vector3 v2 = new Vector3(4, 5, 6);
        Vector3 result = v1.add(v2);

        assertEquals(5.0, result.x, 1e-9, "X component should be 5");
        assertEquals(7.0, result.y, 1e-9, "Y component should be 7");
        assertEquals(9.0, result.z, 1e-9, "Z component should be 9");
    }

    @Test
    void subtract() {
        Vector3 v1 = new Vector3(4, 5, 6);
        Vector3 v2 = new Vector3(1, 2, 3);
        Vector3 result = v1.subtract(v2);

        assertEquals(3.0, result.x, 1e-9, "X component should be 3");
        assertEquals(3.0, result.y, 1e-9, "Y component should be 3");
        assertEquals(3.0, result.z, 1e-9, "Z component should be 3");
    }

    @Test
    void scale() {
        Vector3 v = new Vector3(1, 2, 3);
        double scalar = 2.0;
        Vector3 result = v.scale(scalar);

        assertEquals(2.0, result.x, 1e-9, "X component should be scaled to 2");
        assertEquals(4.0, result.y, 1e-9, "Y component should be scaled to 4");
        assertEquals(6.0, result.z, 1e-9, "Z component should be scaled to 6");
    }

    @Test
    void dot() {
        Vector3 v1 = new Vector3(1, 2, 3);
        Vector3 v2 = new Vector3(4, 5, 6);
        double result = v1.dot(v2);

        assertEquals(32.0, result, 1e-9, "Dot product should be 32");
    }

    @Test
    void cross() {
        Vector3 v1 = new Vector3(1, 2, 3);
        Vector3 v2 = new Vector3(4, 5, 6);
        Vector3 result = v1.cross(v2);

        assertEquals(-3.0, result.x, 1e-9, "X component should be -3");
        assertEquals(6.0, result.y, 1e-9, "Y component should be 6");
        assertEquals(-3.0, result.z, 1e-9, "Z component should be -3");
    }

    @Test
    void magnitude() {
        Vector3 v = new Vector3(3, 4, 0);
        double result = v.magnitude();

        assertEquals(5.0, result, 1e-9, "Magnitude should be 5");
    }

    @Test
    void normalize() {
        Vector3 v = new Vector3(3, 4, 0);
        Vector3 result = v.normalize();

        assertEquals(1.0, result.magnitude(), 1e-9, "Normalized vector magnitude should be 1");
        assertEquals(0.6, result.x, 1e-9, "X component should be 0.6");
        assertEquals(0.8, result.y, 1e-9, "Y component should be 0.8");
        assertEquals(0.0, result.z, 1e-9, "Z component should be 0");
    }
}

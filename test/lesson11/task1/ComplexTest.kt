package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ComplexTest {

    private fun assertApproxEquals(expected: Complex, actual: Complex, eps: Double) {
        assertEquals(expected.re, actual.re, eps)
        assertEquals(expected.im, actual.im, eps)
    }

    @Test
    fun plus() {
        assertApproxEquals(Complex("4-2i"), Complex("1+2i") + Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("3+4i"), Complex("1+3i") + Complex("2+1i"), 1e-10)
    }

    @Test
    operator fun unaryMinus() {
        assertApproxEquals(Complex(-2.0, 1.0), -Complex(2.0, -1.0), 1e-10)
        assertApproxEquals(Complex(-3.6, -13.0), -Complex(3.6, 13.0), 1e-10)
    }

    @Test
    fun minus() {
        assertApproxEquals(Complex("-2+6i"), Complex("4+2i") - Complex("6-4i"), 1e-10)
        assertApproxEquals(Complex("-1+2i"), Complex("1+3i") - Complex("2+1i"), 1e-10)
    }

    @Test
    fun times() {
        assertApproxEquals(Complex("11+2i"), Complex("1+2i") * Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("11+13i"), Complex("1+3i") * Complex("5-2i"), 1e-10)
        assertApproxEquals(Complex("-1+7i"), Complex("1+3i") * Complex("2+1i"), 1e-10)
    }

    @Test
    fun div() {
        assertApproxEquals(Complex("2.6+0.8i"), Complex("11-8i") / Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("1+1i"), Complex("-1+3i") / Complex("1+2i"), 1e-10)
        assertApproxEquals(Complex("1+1i"), Complex("1+3i") / Complex("2+1i"), 1e-10)
    }

    @Test
    fun equals() {
        assertApproxEquals(Complex(1.0, 2.0), Complex("1+2i"), 1e-12)
        assertApproxEquals(Complex(1.0, 0.0), Complex(1.0), 1e-12)
        assertApproxEquals(Complex(4.0, 1.0), Complex("4+1i"), 1e-12)
        assertApproxEquals(Complex(2.7, -8.8), Complex("2.7-8.8i"), 1e-12)
    }

    @Test
    fun toStringTest() {
        assertEquals("14.0", Complex(14.0, 0.0).toString())
        assertEquals("7.0-5.6i", Complex(7.0, -5.6).toString())
        assertEquals("14.0+5.0i", Complex(14.0, 5.0).toString())
        assertEquals("0.0", Complex(0.0, 0.0).toString())
        assertEquals("5.0i", Complex(0.0, 5.0).toString())
    }
}
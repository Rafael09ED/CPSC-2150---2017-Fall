package cpsc2150.lab4;

import org.junit.*;
import static org.junit.Assert.*;

public class TestCalculator {

    private Calculator calc;

    @Before  // runs before each test marked with an @Test annotation
    public void setUp() {
        calc = new Calculator();
    }

    @After //runs after each method marked with an @Test annotation
    public void tearDown() {
        calc = null;
    }

    @Test
    public void testAdd() {
        assertEquals("testing add(3,4)", 7, calc.add(3, 4), .00001);
        assertEquals("testing add(0,0)", 0, calc.add(0, 0), .00001);
        assertEquals("testing add(Float.MAX_VALUE, 0)", Float.MAX_VALUE, calc.add(Float.MAX_VALUE, 0), .00001);
    }

    @Test
    public void testSubtract() {
        assertEquals("testing subtract(3,4)", -1, calc.subtract(3, 4), .00001);
        assertEquals("testing subtract(0,0)", 0, calc.subtract(0, 0), .00001);
        assertEquals("testing subtract(Float.MAX_VALUE, Float.MAX_VALUE)", 0, calc.subtract(Float.MAX_VALUE, Float.MAX_VALUE), .00001);
    }

    @Test
    public void testMult() {
        assertEquals("testing mult(3,4)", 12, calc.mult(3, 4), .00001);
        assertEquals("testing mult(10,0)", 0, calc.mult(10, 0), .00001);
        assertEquals("testing mult(Float.MAX_VALUE, 1)", Float.MAX_VALUE, calc.mult(Float.MAX_VALUE, 1), .00001);
    }

    @Test
    public void testDivide() {
        assertEquals("testing divide(2,4)", .5, calc.divide(2, 4), .00001);
        assertEquals("testing divide(1,3)", .33333333, calc.divide(1, 3), .00001);
        assertEquals("testing divide(Float.MAX_VALUE, Float.MAX_VALUE)", 1, calc.divide(Float.MAX_VALUE, Float.MAX_VALUE), .00001);
    }
}
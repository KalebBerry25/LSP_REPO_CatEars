package org.howard.edu.lsp.finalexam.question3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GradeCalculatorTest {

    GradeCalculator gc = new GradeCalculator();

    @Test
    public void testAverage() {
        double result = gc.average(90, 80, 70);
        assertEquals(80.0, result);
    }

    @Test
    public void testLetterGrade() {
        assertEquals("A", gc.letterGrade(95));
    }

    @Test
    public void testIsPassing() {
        assertTrue(gc.isPassing(70));
    }

    // Boundary tests
    @Test
    public void testBoundaryPassing() {
        assertTrue(gc.isPassing(60));
    }

    @Test
    public void testBoundaryFailing() {
        assertFalse(gc.isPassing(59.9));
    }

    // Exception tests
    @Test
    public void testInvalidLowScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            gc.average(-1, 50, 60);
        });
    }

    @Test
    public void testInvalidHighScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            gc.average(101, 50, 60);
        });
    }
}

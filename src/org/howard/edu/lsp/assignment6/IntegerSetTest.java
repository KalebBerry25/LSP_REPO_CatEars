package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test cases for IntegerSet.
 */
public class IntegerSetTest {

    @Test
    public void testClearNormal() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        set.clear();

        assertEquals("[]", set.toString());
        assertTrue(set.isEmpty());
    }

    @Test
    public void testClearEdgeAlreadyEmpty() {
        IntegerSet set = new IntegerSet();

        set.clear();

        assertEquals("[]", set.toString());
        assertEquals(0, set.length());
    }

    @Test
    public void testLengthNormal() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(20);

        assertEquals(2, set.length());
    }

    @Test
    public void testLengthEdgeEmpty() {
        IntegerSet set = new IntegerSet();

        assertEquals(0, set.length());
    }

    @Test
    public void testEqualsNormalSameElementsDifferentOrder() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(3);
        b.add(1);
        b.add(2);

        assertTrue(a.equals(b));
    }

    @Test
    public void testEqualsEdgeMismatch() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        b.add(1);

        assertFalse(a.equals(b));
    }

    @Test
    public void testContainsNormalPresent() {
        IntegerSet set = new IntegerSet();
        set.add(7);

        assertTrue(set.contains(7));
    }

    @Test
    public void testContainsEdgeAbsent() {
        IntegerSet set = new IntegerSet();
        set.add(7);

        assertFalse(set.contains(99));
    }

    @Test
    public void testLargestNormal() throws Exception {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(5);
        set.add(3);

        assertEquals(5, set.largest());
    }

    @Test
    public void testLargestEdgeSingleElement() throws Exception {
        IntegerSet set = new IntegerSet();
        set.add(42);

        assertEquals(42, set.largest());
    }


    @Test
    public void testSmallestNormal() throws Exception {
        IntegerSet set = new IntegerSet();
        set.add(8);
        set.add(2);
        set.add(10);

        assertEquals(2, set.smallest());
    }

    @Test
    public void testSmallestEdgeSingleElement() throws Exception {
        IntegerSet set = new IntegerSet();
        set.add(42);

        assertEquals(42, set.smallest());
    }


    @Test
    public void testAddNormal() {
        IntegerSet set = new IntegerSet();

        set.add(4);

        assertEquals("[4]", set.toString());
    }

    @Test
    public void testAddEdgeDuplicateValue() {
        IntegerSet set = new IntegerSet();

        set.add(4);
        set.add(4);

        assertEquals("[4]", set.toString());
        assertEquals(1, set.length());
    }

    @Test
    public void testRemoveNormal() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        set.remove(1);

        assertEquals("[2]", set.toString());
    }

    @Test
    public void testRemoveEdgeValueNotPresent() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        set.remove(99);

        assertEquals("[1, 2]", set.toString());
        assertEquals(2, set.length());
    }

    @Test
    public void testUnionNormal() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        b.add(2);
        b.add(3);

        IntegerSet result = a.union(b);

        assertEquals("[1, 2, 3]", result.toString());
        assertEquals("[1, 2]", a.toString());
        assertEquals("[2, 3]", b.toString());
    }

    @Test
    public void testUnionEdgeWithEmptySet() {
        IntegerSet a = new IntegerSet();
        IntegerSet empty = new IntegerSet();

        a.add(1);
        a.add(2);

        IntegerSet result = a.union(empty);

        assertEquals("[1, 2]", result.toString());
    }

    @Test
    public void testIntersectNormal() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(2);
        b.add(3);
        b.add(4);

        IntegerSet result = a.intersect(b);

        assertEquals("[2, 3]", result.toString());
    }

    @Test
    public void testIntersectEdgeNoCommonElements() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);

        b.add(3);
        b.add(4);

        IntegerSet result = a.intersect(b);

        assertEquals("[]", result.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDiffNormal() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(2);
        b.add(3);
        b.add(4);

        IntegerSet result = a.diff(b);

        assertEquals("[1]", result.toString());
    }

    @Test
    public void testDiffEdgeIdenticalSets() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);

        b.add(1);
        b.add(2);

        IntegerSet result = a.diff(b);

        assertEquals("[]", result.toString());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testComplementNormal() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(2);
        b.add(3);
        b.add(4);

        IntegerSet result = a.complement(b);

        assertEquals("[4]", result.toString());
    }

    @Test
    public void testComplementEdgeDisjointSets() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);

        b.add(3);
        b.add(4);

        IntegerSet result = a.complement(b);

        assertEquals("[3, 4]", result.toString());
    }

    @Test
    public void testIsEmptyNormalEmptySet() {
        IntegerSet set = new IntegerSet();

        assertTrue(set.isEmpty());
    }

    @Test
    public void testIsEmptyEdgeNonEmptySet() {
        IntegerSet set = new IntegerSet();
        set.add(1);

        assertFalse(set.isEmpty());
    }

    @Test
    public void testToStringNormal() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(1);
        set.add(2);

        assertEquals("[1, 2, 3]", set.toString());
    }

    @Test
    public void testToStringEdgeEmptySet() {
        IntegerSet set = new IntegerSet();

        assertEquals("[]", set.toString());
    }
}

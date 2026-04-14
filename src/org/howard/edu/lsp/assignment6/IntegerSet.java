package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a set of integers.
 * Duplicate values are not allowed.
 */
public class IntegerSet {
    
    private ArrayList<Integer> set = new ArrayList<Integer>();

    /**
     * Default constructor.
     */
    public IntegerSet() {
    }

    /**
     * Clears the internal set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the length of the set.
     * 
     * @return number of elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Compares this set to another set for equality to see if they contain the same elements.
     * 
     * @param b the other IntegerSet
     * @return true if both sets contain the same elements, false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }
        if (this.length() != b.length()) {
            return false;
        }

        for (int value : this.set) {
            if (!b.contains(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the set contains the given value.
     * 
     * @param value the value to search for
     * @return true if found, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * @return largest integer in the set
     */
    public int largest()  {
        return Collections.max(set);
    }

    /**
     * 
     * @return smallest integer in the set
     */
    public int smallest() {
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     * 
     * @param item the value to add
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it exists.
     * 
     * @param item the value to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set that is the union of this set and another set.
     * 
     * @param intSetb the other set
     * @return new IntegerSet containing all elements in either set
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (int value : this.set) {
            result.add(value);
        }

        if (intSetb != null) {
            for (int value : intSetb.set) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set that is the intersection of this set and another set.
     * 
     * @param intSetb the other set
     * @return new IntegerSet containing elements common to both sets
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (int value : this.set) {
            if (intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in this set but not in the other set.
     * 
     * @param intSetb the other set
     * @return new IntegerSet representing the difference
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            for (int value : this.set) {
                result.add(value);
            }
            return result;
        }

        for (int value : this.set) {
            if (!intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in the other set
     * that are not in this set.
     * 
     * @param intSetb the other set
     * @return new IntegerSet representing the complement
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (int value : intSetb.set) {
            if (!this.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * 
     * @return string representation of the set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<Integer>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}
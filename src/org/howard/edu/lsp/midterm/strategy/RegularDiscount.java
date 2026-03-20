package org.howard.edu.lsp.midterm.strategy;

/**
 * Discount strategy for regular customers.
 * No discount is applied.
 *
 * @author Kaleb Berry
 */
public class RegularDiscount implements DiscountStrategy {

    /**
     * Returns the original price with no discount applied.
     *
     * @param price the original purchase price
     * @return the unchanged price
     */
    @Override
    public double calculate(double price) {
        return price;
    }
}
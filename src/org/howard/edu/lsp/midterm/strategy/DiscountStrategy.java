package org.howard.edu.lsp.midterm.strategy;

/**
 * Defines the strategy for calculating a final price.
 *
 * @author Kaleb Berry
 */
public interface DiscountStrategy {

    /**
     * Calculates the final price after applying the discount strategy.
     *
     * @param price the original purchase price
     * @return the final discounted price
     */
    double calculate(double price);
}

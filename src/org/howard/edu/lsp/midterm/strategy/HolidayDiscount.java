package org.howard.edu.lsp.midterm.strategy;

/**
 * Discount strategy for holiday purchases.
 * Applies a 15 percent discount.
 *
 * @author Kaleb Berry
 */
public class HolidayDiscount implements DiscountStrategy {

    /**
     * Returns the final price after applying a 15 percent discount.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    @Override
    public double calculate(double price) {
        return price * 0.85;
    }
}

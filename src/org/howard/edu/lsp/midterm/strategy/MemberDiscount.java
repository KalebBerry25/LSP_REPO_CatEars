package org.howard.edu.lsp.midterm.strategy;

/**
 * Discount strategy for member customers.
 * Applies a 10 percent discount.
 *
 * @author Kaleb Berry
 */
public class MemberDiscount implements DiscountStrategy {

    /**
     * Returns the final price after applying a 10 percent discount.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    @Override
    public double calculate(double price) {
        return price * 0.90;
    }
}

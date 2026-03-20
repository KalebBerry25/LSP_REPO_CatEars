package org.howard.edu.lsp.midterm.strategy;

/**
 * Discount strategy for VIP customers.
 * Applies a 20 percent discount.
 *
 * @author Kaleb Berry
 */
public class VipDiscount implements DiscountStrategy {

    /**
     * Returns the final price after applying a 20 percent discount.
     *
     * @param price the original purchase price
     * @return the discounted price
     */
    @Override
    public double calculate(double price) {
        return price * 0.80;
    }
}
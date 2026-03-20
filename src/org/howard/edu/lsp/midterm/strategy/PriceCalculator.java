package org.howard.edu.lsp.midterm.strategy;

/**
 * Uses a discount strategy to calculate a final price.
 *
 * @author Kaleb Berry
 */
public class PriceCalculator {

    private DiscountStrategy discountStrategy;

    /**
     * Sets the discount strategy to use for price calculation.
     *
     * @param discountStrategy the discount strategy
     */
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * Calculates the final price using the current discount strategy.
     *
     * @param price the original purchase price
     * @return the final price after discount
     */
    public double calculatePrice(double price) {
        return discountStrategy.calculate(price);
    }
}

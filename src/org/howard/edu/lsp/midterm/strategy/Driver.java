package org.howard.edu.lsp.midterm.strategy;

/**
 * Demonstrates the Strategy Pattern implementation for price calculation.
 *
 * @author Kaleb Berry
 */
public class Driver {

    /**
     * Runs the pricing strategy demonstration.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();
        double price = 100.0;

        calculator.setDiscountStrategy(new RegularDiscount());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        calculator.setDiscountStrategy(new MemberDiscount());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        calculator.setDiscountStrategy(new VipDiscount());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        calculator.setDiscountStrategy(new HolidayDiscount());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}
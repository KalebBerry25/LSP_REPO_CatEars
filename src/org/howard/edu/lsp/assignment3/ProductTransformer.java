package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class ProductTransformer {

    private static final BigDecimal DISCOUNT = new BigDecimal("0.90");
    private static final BigDecimal PREMIUM_THRESHOLD = new BigDecimal("500.00");

    public TransformedRow transform(Product p) {
        // 1) UPPERCASE name 
        p = p.withName(p.getName().toUpperCase(Locale.ROOT));

        // 2) Electronics discount 
        if ("Electronics".equals(p.getCategory())) {
            p = p.withPrice(p.getPrice().multiply(DISCOUNT));
        }

        // Round HALF_UP to 2 decimals 
        p = p.withPrice(p.getPrice().setScale(2, RoundingMode.HALF_UP));

        // 3) Premium Electronics based on final rounded price + original category 
        if ("Electronics".equals(p.getOriginalCategory())
                && p.getPrice().compareTo(PREMIUM_THRESHOLD) > 0) {
            p = p.withCategory("Premium Electronics");
        }

        // 4) PriceRange from final rounded price 
        String priceRange = determinePriceRange(p.getPrice());

        return new TransformedRow(
                p.getProductId(),
                p.getName(),
                p.getPrice(),      
                p.getCategory(),
                priceRange
        );
    }

    private String determinePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
        if (price.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
        if (price.compareTo(new BigDecimal("500.00")) <= 0) return "High";
        return "Premium";
    }

    // Small nested output type to avoid another class file
    public static class TransformedRow {
        public final int productId;
        public final String name;
        public final BigDecimal price;
        public final String category;
        public final String priceRange;

        public TransformedRow(int productId, String name, BigDecimal price, String category, String priceRange) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.category = category;
            this.priceRange = priceRange;
        }
    }
}

/**
 * Name: Kaleb Berry
 */

package org.howard.edu.lsp.assignment2;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class ETLPipeline {

    /**
 *  This looks for "data/products.csv" as input and writes to "data/transformed_products.csv" as output.
 *  It looks fpr these relative to the current working directory and will create the output file there as well
    */
    private static final Path INPUT_PATH = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    public static void main(String[] args) {
        int rowsRead = 0;        
        int rowsTransformed = 0; 
        int rowsSkipped = 0;


        /**
 * Exit if input file does not exist
 */
        if (!Files.exists(INPUT_PATH)) {
            System.out.println("Error: Missing input file: " + INPUT_PATH.toString());
            return; 
        }

        try {
        /**
 * Create output directory if it doesn't exist
 */
            if (OUTPUT_PATH.getParent() != null) {
                Files.createDirectories(OUTPUT_PATH.getParent());
            }

            /**
 * Initialize readers and writers i/o streams
 */
            try (BufferedReader reader = Files.newBufferedReader(INPUT_PATH, StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(OUTPUT_PATH, StandardCharsets.UTF_8)) {

                String header = reader.readLine();

                writer.write("ProductID,Name,Price,Category,PriceRange");
                writer.newLine();

                /**
 * If header is missing, treat as empty file (0 rows read) and exit with summary
 */
                if (header == null) {
                    printSummary(rowsRead, rowsTransformed, rowsSkipped, OUTPUT_PATH);
                    return;
                }

                String line;
                while ((line = reader.readLine()) != null) {
                    rowsRead++;

                    /**
 * Checks for empty lines, incorrect rows and unparseable product ids to skip
 */
                    if (line.trim().isEmpty()) {
                        rowsSkipped++;
                        continue;
                    }

                    String[] parts = line.split(",", -1);
                    if (parts.length != 4) {
                        rowsSkipped++;
                        continue;
                    }
/**
 * splits rows into parts for transformations
 */
                    String productIdStr = parts[0].trim();
                    String name = parts[1].trim();
                    String priceStr = parts[2].trim();
                    String category = parts[3].trim();

                    int productId;
                    BigDecimal price;
                    try {
                        productId = Integer.parseInt(productIdStr);
                        price = new BigDecimal(priceStr);
                    } catch (Exception e) {
                        rowsSkipped++;
                        continue;
                    }

                    

                    // 1) Name -> UPPERCASE
                    String transformedName = name.toUpperCase(Locale.ROOT);

                    // Keep original category for rule #3
                    String originalCategory = category;

                    // 2) If category is "Electronics", apply 10% discount
                    BigDecimal transformedPrice = price;
                    if ("Electronics".equals(category)) {
                        transformedPrice = transformedPrice.multiply(new BigDecimal("0.90"));
                    }

                    // Round price 
                    transformedPrice = transformedPrice.setScale(2, RoundingMode.HALF_UP);

                    // 3) If final rounded price > 500.00 AND original category was "Electronics",
                    //    change category to "Premium Electronics"
                    String transformedCategory = category;
                    if ("Electronics".equals(originalCategory)
                            && transformedPrice.compareTo(new BigDecimal("500.00")) > 0) {
                        transformedCategory = "Premium Electronics";
                    }

                    // 4) Add PriceRange based on final rounded price
                    String priceRange = determinePriceRange(transformedPrice);

                    /**
 * Writes transformed data to output file
 */
                    writer.write(productId + "," +
                            transformedName + "," +
                            formatTwoDecimals(transformedPrice) + "," +
                            transformedCategory + "," +
                            priceRange);
                    writer.newLine();

                    rowsTransformed++;
                }
            }

            printSummary(rowsRead, rowsTransformed, rowsSkipped, OUTPUT_PATH);

        } catch (IOException e) {
            System.out.println("Error: Unable to process files. " + e.getMessage());
        }
    }

    private static String determinePriceRange(BigDecimal finalRoundedPrice) {
        // <= 10.00 -> Low
        // > 10.00 and <= 100.00 -> Medium
        // > 100.00 and <= 500.00 -> High
        // > 500.00 -> Premium
        if (finalRoundedPrice.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
        if (finalRoundedPrice.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
        if (finalRoundedPrice.compareTo(new BigDecimal("500.00")) <= 0) return "High";
        return "Premium";
    }

    private static String formatTwoDecimals(BigDecimal value) {
        // Ensure exactly two decimal places in output
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, Path outputPath) {
        System.out.println("Run Summary");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + outputPath.toString());
    }
}



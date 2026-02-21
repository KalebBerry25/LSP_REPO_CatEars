package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ETLPipelineRunner {

    private final Path inputPath;
    private final Path outputPath;
    private final ProductTransformer transformer = new ProductTransformer();

    public ETLPipelineRunner(Path inputPath, Path outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void run() {
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        // Missing input file handling 
        if (!Files.exists(inputPath)) {
            System.out.println("Error: Missing input file: " + inputPath.toString());
            return;
        }

        try {
            if (outputPath.getParent() != null) {
                Files.createDirectories(outputPath.getParent());
            }

            try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {

                // Always write header to output 
                writer.write("ProductID,Name,Price,Category,PriceRange");
                writer.newLine();

                // Read and ignore input header 
                String header = reader.readLine();
                if (header == null) {
                    // Empty file: header-only output required 
                    printSummary(rowsRead, rowsTransformed, rowsSkipped);
                    return;
                }

                String line;
                while ((line = reader.readLine()) != null) {
                    rowsRead++; // counts all non-header lines, even bad ones 

                    Product parsed = parseLineToProductOrNull(line);
                    if (parsed == null) {
                        rowsSkipped++;
                        continue;
                    }

                    ProductTransformer.TransformedRow out = transformer.transform(parsed);

                    //Writes transformed data to output file
                    writer.write(out.productId + "," +
                            out.name + "," +
                            formatTwoDecimals(out.price) + "," +
                            out.category + "," +
                            out.priceRange);
                    writer.newLine();

                    rowsTransformed++;
                }
            }

            printSummary(rowsRead, rowsTransformed, rowsSkipped);

        } catch (IOException e) {
            System.out.println("Error: Unable to process files. " + e.getMessage());
        }
    }

    private Product parseLineToProductOrNull(String line) {
        //Checks for empty lines, incorrect rows and unparseable product ids to skip
        if (line == null || line.trim().isEmpty()) return null;

        String[] parts = line.split(",", -1);
        if (parts.length != 4) return null;

        //splits rows into parts for transformations
        String productIdStr = parts[0].trim();
        String name = parts[1].trim();
        String priceStr = parts[2].trim();
        String category = parts[3].trim();

        try {
            int productId = Integer.parseInt(productIdStr);
            BigDecimal price = new BigDecimal(priceStr);
            return new Product(productId, name, price, category);
        } catch (Exception e) {
            return null;
        }
    }

    private String formatTwoDecimals(BigDecimal value) {
        // Must always print exactly two decimals 
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped) {
        System.out.println("Run Summary");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + outputPath.toString());
    }
}

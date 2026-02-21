package org.howard.edu.lsp.assignment3;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ETLPipeline {
    /**
    *  This looks for "data/products.csv" as input and writes to "data/transformed_products.csv" as output.
    *  It looks for these relative to the current working directory and will create the output file there as well
    */
    private static final Path INPUT_PATH = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    public static void main(String[] args) {
        new ETLPipelineRunner(INPUT_PATH, OUTPUT_PATH).run();
    }
}
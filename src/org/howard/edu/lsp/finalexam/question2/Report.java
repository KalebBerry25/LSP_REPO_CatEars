package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class using Template Method pattern
 */
public abstract class Report {

    // Template Method (fixed workflow)
    public final void generateReport() {
        loadData();
        System.out.println("=== HEADER ===");
        System.out.println(formatHeader());

        System.out.println("\n=== BODY ===");
        System.out.println(formatBody());

        System.out.println("\n=== FOOTER ===");
        System.out.println(formatFooter());
        System.out.println();
    }

    protected abstract void loadData();
    protected abstract String formatHeader();
    protected abstract String formatBody();
    protected abstract String formatFooter();
}


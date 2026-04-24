Report.java

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


StudentReport.java

package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete implementation for student report
 */
public class StudentReport extends Report {

    private String studentName;
    private double gpa;

    @Override
    protected void loadData() {
        this.studentName = "John Doe";
        this.gpa = 3.8;
    }

    @Override
    protected String formatHeader() {
        return "Student Report";
    }

    @Override
    protected String formatBody() {
        return "Student Name: " + studentName + "\nGPA: " + gpa;
    }

    @Override
    protected String formatFooter() {
        return "End of Student Report";
    }
}


CourseReport.java

package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete implementation for course report
 */
public class CourseReport extends Report {

    private String courseName;
    private int enrollment;

    @Override
    protected void loadData() {
        this.courseName = "CSCI 363";
        this.enrollment = 45;
    }

    @Override
    protected String formatHeader() {
        return "Course Report";
    }

    @Override
    protected String formatBody() {
        return "Course: " + courseName + "\nEnrollment: " + enrollment;
    }

    @Override
    protected String formatFooter() {
        return "End of Course Report";
    }
}

Driver.java


package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();

        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}

Explanation:

The Template Method pattern is implemented in the Report class through the generateReport() method, which defines a fixed workflow for generating reports. The steps loadData(), formatHeader(), formatBody(), and formatFooter() are declared as abstract methods and are implemented by subclasses. The StudentReport and CourseReport classes provide their own specific formatting while reusing the same overall structure defined in the base class. This approach promotes code reuse and ensures consistent report generation while still allowing for customization in each report type.
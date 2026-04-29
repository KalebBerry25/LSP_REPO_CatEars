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

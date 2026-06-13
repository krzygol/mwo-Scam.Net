package org.example.display;

import org.example.report.Report;

public class Displayer {

    public void display(Report report) {
        System.out.println("=== " + report.getTitle() + " ===");
        System.out.println(report.generate());
        System.out.println();
    }
}

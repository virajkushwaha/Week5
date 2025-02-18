package com.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortEmployeesBySalary {
    public static void main(String[] args) {
        // File path to employees.csv
        String filename = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\employees.csv";

        // Call method to sort and display top 5 employees
        sortAndDisplayTopSalaries(filename);
    }

    // Method to sort records by salary in descending order and print top 5 employees
    public static void sortAndDisplayTopSalaries(String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> records = reader.readAll(); 

            if (records.isEmpty()) {
                System.out.println("The CSV file is empty!");
                return;
            }

            // Remove the header row
            String[] header = records.remove(0);

            // Sort records by salary in descending order
            records.sort((a, b) -> {
                try {
                    double salaryA = Double.parseDouble(a[3].trim());
                    double salaryB = Double.parseDouble(b[3].trim());
                    return Double.compare(salaryB, salaryA); // Sort in descending order
                } catch (NumberFormatException e) {
                    return 0;
                }
            });

            // Print header
            System.out.println("\nTop 5 Highest-Paid Employees:");
            System.out.println(String.join(", ", header));

            // Print top 5 records (or all if less than 5 exist)
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                System.out.println(String.join(", ", records.get(i)));
            }

        } catch (IOException | CsvException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}


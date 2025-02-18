package com.intermediateproblems;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SearchEmployee {
    public static void main(String[] args) {
        // Path to the employees.csv file
        String filename = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\employees.csv";



        String searchName = "Aditya";

        searchEmployeeByName(filename, searchName);
    }

    // Method to search employee by name and print details
    public static void searchEmployeeByName(String filename, String searchName) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> records = reader.readAll(); // Read all records at once

            if (records.isEmpty()) {
                System.out.println("The CSV file is empty!");
                return;
            }

            boolean found = false;
            System.out.println("\nSearching for Employee: " + searchName + "\n");

            // Skip header (index 0) and iterate through records
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);

                if (data.length >= 4) { // Ensure data has expected fields
                    String name = data[1].trim();  // Employee Name
                    String department = data[2].trim();  // Department
                    String salary = data[3].trim();  // Salary

                    if (name.equalsIgnoreCase(searchName)) { // Case-insensitive search
                        System.out.println("Employee Found!");
                        System.out.println("Department: " + department);
                        System.out.println("Salary:" + salary);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Employee Not Found!");
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}

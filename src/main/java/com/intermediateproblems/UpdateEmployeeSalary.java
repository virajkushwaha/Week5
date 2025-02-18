package com.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class UpdateEmployeeSalary {
    public static void main(String[] args) {
        // File paths
        String inputFilename = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\employees.csv";
        String outputFilename = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\updated_employees.csv";

        updateSalary(inputFilename, outputFilename);
    }

    // Method to increase salary by 10% for IT department
    public static void updateSalary(String inputFilename, String outputFilename) {
        try (
                CSVReader reader = new CSVReader(new FileReader(inputFilename));
                CSVWriter writer = new CSVWriter(new FileWriter(outputFilename))
        ) {
            List<String[]> records = reader.readAll();

            if (records.isEmpty()) {
                System.out.println("The CSV file is empty!");
                return;
            }

            // Iterate through records and update salaries for IT employees
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);

                if (data.length >= 4) {
                    String department = data[2].trim();
                    String salaryStr = data[3].trim();

                    if (department.equalsIgnoreCase("IT")) {
                        double salary = Double.parseDouble(salaryStr);
                        salary *= 1.10;
                        data[3] = String.format("%.2f", salary);
                    }
                }
            }

            // Write updated records to new file
            writer.writeAll(records);
            System.out.println("Updated salaries saved to: " + outputFilename);

        } catch (IOException | CsvException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing salary values. Ensure correct format in CSV.");
        }
    }
}

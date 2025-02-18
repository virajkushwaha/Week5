package com.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FilterRecords {
    public static void main(String[] args) {
        int qualifyingMarks = 80;
        String filename = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\students.csv";
        printQualifyingRecords(qualifyingMarks, filename);
    }

    // Method to filter and print students with marks >= given threshold
    public static void printQualifyingRecords(int marks, String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> records = reader.readAll();

            if (records.isEmpty()) {
                System.out.println("The CSV file is empty!");
                return;
            }

            System.out.println("\nStudents with marks >= " + marks + ":\n");

            // Skip the header row (index 0)
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);

                if (data.length >= 4) { // Ensure valid data
                    int studentMarks = Integer.parseInt(data[3].trim());
                    if (studentMarks >= marks) {
                        System.out.println(String.join(",", data));
                    }
                }
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing marks: Ensure correct number format in CSV.");
        }
    }
}

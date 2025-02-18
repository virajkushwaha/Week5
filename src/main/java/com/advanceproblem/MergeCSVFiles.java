package com.advanceproblem;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MergeCSVFiles {
    public static void main(String[] args) {
        String file1 = "src/main/java/com/advanceproblem/student1.csv";
        String file2 = "src/main/java/com/advanceproblem/student2.csv";
        String outputFile = "src/main/java/com/advanceproblem/mergeFile.csv";

        mergeCSVFiles(file1, file2, outputFile);
    }

    // Method to merge two CSV files based on ID
    public static void mergeCSVFiles(String file1, String file2, String outputFile) {
        Map<String, String[]> studentData = new HashMap<>();

        try {
            // Read first CSV (ID, Name, Age)
            try (CSVReader reader1 = new CSVReader(new FileReader(file1))) {
                List<String[]> records = reader1.readAll();
                for (int i = 1; i < records.size(); i++) {
                    String[] data = records.get(i);
                    if (data.length >= 3) {
                        studentData.put(data[0], new String[]{data[1], data[2]});
                    }
                }
            }

            // Read second CSV (ID, Marks, Grade)
            try (CSVReader reader2 = new CSVReader(new FileReader(file2))) {
                List<String[]> records = reader2.readAll();
                for (int i = 1; i < records.size(); i++) {
                    String[] data = records.get(i);
                    if (data.length >= 3 && studentData.containsKey(data[0])) {
                        String[] existingData = studentData.get(data[0]);
                        studentData.put(data[0], new String[]{existingData[0], existingData[1], data[1], data[2]});
                    }
                }
            }

            // Write merged data to a new CSV file
            try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
                // Writing header row
                String[] header = {"ID", "Name", "Age", "Marks", "Grade"};
                writer.writeNext(header);

                // Writing merged records
                for (Map.Entry<String, String[]> entry : studentData.entrySet()) {
                    String[] record = new String[]{entry.getKey(), entry.getValue()[0], entry.getValue()[1], entry.getValue()[2], entry.getValue()[3]};
                    writer.writeNext(record);
                }
            }

            System.out.println("CSV files merged successfully into: " + outputFile);

        } catch (IOException | CsvException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

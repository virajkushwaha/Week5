package com.advanceproblem;



import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DetectDuplicateCSV {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/advanceproblem/employees.csv"; // Path to CSV file
        detectDuplicates(filePath);
    }

    // Method to read CSV and detect duplicate IDs
    public static void detectDuplicates(String filePath) {
        Set<String> uniqueIds = new HashSet<>(); // Store unique IDs
        boolean headerSkipped = false;
        int duplicateCount = 0;

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;

            while ((line = reader.readNext()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true; // Skip header
                    continue;
                }

                String id = line[0].trim(); // Extract ID (Assuming it's in column 0)

                // Check for duplicates
                if (uniqueIds.contains(id)) {
                    duplicateCount++;
                    System.out.println("🔴 Duplicate Found: " + String.join(", ", line));
                } else {
                    uniqueIds.add(id);
                }
            }

            System.out.println("\n✅ Processing complete. Total Duplicates Found: " + duplicateCount);
        } catch (IOException | CsvValidationException e) {
            System.out.println("❌ Error reading CSV: " + e.getMessage());
        }
    }
}

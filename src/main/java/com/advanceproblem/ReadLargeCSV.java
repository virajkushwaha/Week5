package com.advanceproblem;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadLargeCSV {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/advanceproblem/21mb.csv";
        int batchSize = 100;
        readCSVInChunks(filePath, batchSize);
    }

    // Method to read and process CSV in chunks
    public static void readCSVInChunks(String filePath, int batchSize) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> batch = new ArrayList<>();
            int totalRecords = 0;
            String[] line;

            // Read & skip the header
            String[] header = reader.readNext();
            if (header != null) {
                System.out.println("Header: " + String.join(", ", header));
            } else {
                System.out.println("Empty CSV file. No data to process.");
                return;
            }

            // Read one line at a time and process in batches
            while ((line = reader.readNext()) != null) {
                batch.add(line);
                if (batch.size() == batchSize) {
                    totalRecords += batch.size();
                    System.out.println("Processed " + batch.size() + " records. Total: " + totalRecords);
                    System.out.println("Example Record: " + String.join(", ", batch.get(0)));
                    batch.clear();
                }
            }

            // Process remaining records (if any)
            if (!batch.isEmpty()) {
                totalRecords += batch.size();
                System.out.println("Processed " + batch.size() + " records. Total: " + totalRecords);
                System.out.println("Example Record: " + String.join(", ", batch.get(0)));
            }

            System.out.println("Finished processing. Total records: " + totalRecords);
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }
}

package com.advanceproblem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

public class CsvToJsonConverter {
    public static void main(String[] args) {
        String csvFilePath = "src/main/java/com/advanceproblem/students.csv";  // CSV file path
        String jsonFilePath = "src/main/java/com/advanceproblem/students_output.json";  // JSON file path
        convertCsvToJson(csvFilePath, jsonFilePath);
    }

    // Method to convert CSV to JSON
    public static void convertCsvToJson(String csvFile, String jsonFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            // Read all CSV records at once
            List<String[]> records = reader.readAll(); // This can throw CsvException

            if (records.isEmpty()) {
                System.out.println("⚠ No data found in CSV file.");
                return;
            }

            // Extract headers from the first row
            String[] headers = records.get(0);
            List<Map<String, String>> students = new ArrayList<>();

            // Loop through the records and convert each row into a map of header-value pairs
            for (int i = 1; i < records.size(); i++) {
                Map<String, String> student = new HashMap<>();
                for (int j = 0; j < headers.length; j++) {
                    student.put(headers[j], records.get(i)[j]);  // Add key-value pairs
                }
                students.add(student);
            }

            // Write the data as JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), students);

            System.out.println("CSV converted to JSON successfully: " + jsonFile);
        } catch (IOException | CsvException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

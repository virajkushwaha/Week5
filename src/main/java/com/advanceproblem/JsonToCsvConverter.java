package com.advanceproblem;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.List;
import java.util.Map;

public class JsonToCsvConverter {
    public static void main(String[] args) {
        String jsonFilePath = "src/main/java/com/advanceproblem/students.json";
        String csvFilePath = "src/main/java/com/advanceproblem/students.csv";
        convertJsonToCsv(jsonFilePath, csvFilePath);
    }

    // Method to convert JSON to CSV
    public static void convertJsonToCsv(String jsonFile, String csvFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> students = objectMapper.readValue(new File(jsonFile), new TypeReference<>() {});

            // Writing CSV
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
                // Write header
                if (!students.isEmpty()) {
                    writer.writeNext(students.get(0).keySet().toArray(new String[0]));
                }

                // Write data rows
                for (Map<String, Object> student : students) {
                    writer.writeNext(student.values().stream().map(String::valueOf).toArray(String[]::new));
                }
            }
            System.out.println("JSON converted to CSV successfully: " + csvFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

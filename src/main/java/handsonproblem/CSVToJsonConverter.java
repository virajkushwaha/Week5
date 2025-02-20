package handsonproblem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

public class CSVToJsonConverter {
    public static void main(String[] args) {
        String csvInputPath = "ipl_data.csv";   // Input CSV file
        String jsonOutputPath = "ipl_data.json"; // Output JSON file

        try {
            List<Map<String, String>> jsonData = readCsv(csvInputPath);
            writeJson(jsonOutputPath, jsonData);
            System.out.println("✅ CSV successfully converted to JSON: " + jsonOutputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Read CSV and convert it into a List of Maps
    public static List<Map<String, String>> readCsv(String filePath) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            if (rows.isEmpty()) return data;

            String[] headers = rows.get(0); // First row contains column headers

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Map<String, String> jsonObject = new LinkedHashMap<>();

                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], row[j]);
                }
                data.add(jsonObject);
            }
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    //Write JSON data to a file
    public static void writeJson(String filePath, List<Map<String, String>> data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }
}

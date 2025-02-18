package com.advanceproblem;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class ValidateCSVData {
    public static void main(String[] args) {
        // File path to employees.csv
        String filename = "src/main/java/com/advanceproblem/employees.csv";

        // Call method to validate records
        validateCSVData(filename);
    }

    // Method to validate email and phone number
    public static void validateCSVData(String filename) {
        // Define regex patterns
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String phoneRegex = "^[0-9]{10}$";

        // Compile regex patterns
        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> records = reader.readAll();

            if (records.isEmpty()) {
                System.out.println("The CSV file is empty!");
                return;
            }

            // Extract header
            String[] header = records.get(0);
            System.out.println("\nValidating CSV Data...");

            // Iterate through records and validate
            boolean hasErrors = false;
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);

                if (data.length < 5) {
                    System.out.println("Row " + (i + 1) + " is missing required fields.");
                    hasErrors = true;
                    continue;
                }

                String email = data[3].trim(); // Email column
                String phone = data[4].trim(); // Phone column

                boolean emailValid = emailPattern.matcher(email).matches();
                boolean phoneValid = phonePattern.matcher(phone).matches();

                if (!emailValid || !phoneValid) {
                    hasErrors = true;
                    System.out.println("\nInvalid Record at Row " + (i + 1) + ": " + String.join(", ", data));

                    if (!emailValid) {
                        System.out.println("Invalid Email: " + email);
                    }
                    if (!phoneValid) {
                        System.out.println("Invalid Phone Number: " + phone);
                    }
                }
            }

            if (!hasErrors) {
                System.out.println("\n✅ All records are valid.");
            }

        } catch (IOException | CsvException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

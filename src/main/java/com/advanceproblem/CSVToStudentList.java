package com.advanceproblem;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVToStudentList {
    public static void main(String[] args) {
        String filename = "src/main/java/com/basicproblem/students.csv";

        // Convert CSV data into a List<Student>
        List<Student> studentList = readCSVToStudentList(filename);

        // Print the list of students
        if (!studentList.isEmpty()) {
            System.out.println("\nStudent Records from CSV:");
            for (Student student : studentList) {
                System.out.println(student);
            }
        } else {
            System.out.println("No valid student records found!");
        }
    }

    // Method to read CSV and convert rows into Student objects
    public static List<Student> readCSVToStudentList(String filename) {
        List<Student> students = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> records = reader.readAll(); // Read all rows

            if (records.isEmpty()) {
                System.out.println("The CSV file is empty!");
                return students;
            }

            // Skip header row (first row)
            for (int i = 1; i < records.size(); i++) {
                String[] data = records.get(i);

                if (data.length < 4) {
                    System.out.println("Skipping invalid row " + (i + 1) + ": " + String.join(", ", data));
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    int age = Integer.parseInt(data[2].trim());
                    double marks = Double.parseDouble(data[3].trim());

                    // Create a Student object and add to the list
                    students.add(new Student(id, name, age, marks));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid data at row " + (i + 1) + ": " + String.join(", ", data));
                }
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return students;
    }
}


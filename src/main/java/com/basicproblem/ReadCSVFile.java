package com.basicproblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVFile {
    public static void main(String[] args) {
        String filePath = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\students.csv";
        readCSV(filePath);
    }
    //Method for Reading CSV file
    public static void readCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            System.out.println("\nStudent Details:\n");

            while ((line = br.readLine()) != null) {
                if (isHeader) { // Skip header row
                    isHeader = false;
                    continue;
                }
                String[] data = line.split(",");

                // Ensure correct parsing
                if (data.length >= 4) {
                    System.out.println("ID: " + data[0] + ", Name: " + data[1] +
                            ", Age: " + data[2] + ", Marks: " + data[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


package com.basicproblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndCountRow {
    public static void main(String[] args) {
        
        String filename = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\students.csv";
        readAndCountRow(filename);
    }

    public static void readAndCountRow(String filename) {
        int count = 0;


        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isHeader = true; 

            System.out.println("\nStudent Records:\n");

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                System.out.println(line);
                count++;
            }

            System.out.println("\nNumber of Records: " + count);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

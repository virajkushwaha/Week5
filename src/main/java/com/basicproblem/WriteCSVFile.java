package com.basicproblem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVFile {
    public static void main(String[] args) {
        String filename = "E:\\CapgeminiTraining\\Program_Prerequisite\\Week5\\Day1_OpenCSV\\src\\main\\java\\com\\basicproblem\\employees.csv";
        writeCSVFile(filename);
    }
    //Method For Writting the file with five employees
    public static void writeCSVFile(String filename) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename))) {
            br.write("ID,Name,Department,Salary");
            br.newLine();
            br.write("01,Aditya,Maintenance,7984");
            br.newLine();
            br.write("02,Shekhawat,Security,45000");
            br.newLine();
            br.write("03,Rajneesh,Management,566000");
            br.newLine();
            br.write("04,Hashangranga,Cleaning,15000");
            br.newLine();
            br.write("05,Mathur,Kitchen,45800");

            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }
}

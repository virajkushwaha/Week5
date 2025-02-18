package com.advanceproblem;

import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class EncryptDecryptCSV {

    // AES encryption algorithm
    private static final String ALGORITHM = "AES";
    // Example 128-bit AES key (ensure it's 16 characters long for AES-128)
    private static final String ENCRYPTION_KEY = "1234567890123456";  // 16 characters for AES-128
    private static SecretKey secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);

    public static void main(String[] args) {
        String csvFilePath = "src/main/java/com/advanceproblem/employees.csv";  // Input CSV path
        String encryptedCsvFilePath = "src/main/java/com/advanceproblem/encrypted_employees.csv";  // Encrypted CSV path
        String decryptedCsvFilePath = "src/main/java/com/advanceproblem/decrypted_employees.csv";  // Decrypted CSV path

        // Write encrypted data to CSV
        writeEncryptedCSV(csvFilePath, encryptedCsvFilePath);

        // Read and decrypt the data from CSV
        readDecryptedCSV(encryptedCsvFilePath, decryptedCsvFilePath);
    }

    // Method to encrypt data using AES
    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);  // Encoding encrypted data to string
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    // Method to decrypt data using AES
    public static String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedData);  // Decoding decrypted data back to string
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }

    // Method to write encrypted data to a CSV file
    public static void writeEncryptedCSV(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {

            String line;
            // Write the header row
            String[] header = {"ID", "Name", "Email", "Salary"};
            writer.writeNext(header);

            // Read data from CSV and encrypt sensitive fields
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Encrypt sensitive fields
                String encryptedEmail = encrypt(data[2]);  // Email encryption
                String encryptedSalary = encrypt(data[3]);  // Salary encryption

                // Write encrypted data to output CSV
                String[] encryptedData = {data[0], data[1], encryptedEmail, encryptedSalary};
                writer.writeNext(encryptedData);
            }

            System.out.println("✅ Data encrypted and saved to CSV successfully.");
        } catch (Exception e) {
            System.out.println("❌ Error encrypting and writing to CSV: " + e.getMessage());
        }
    }

    // Method to read encrypted CSV and decrypt sensitive fields
    public static void readDecryptedCSV(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             CSVReader reader = new CSVReader(br);
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {

            String[] header = reader.readNext();  // Read and write the header
            writer.writeNext(header);

            String[] line;
            while ((line = reader.readNext()) != null) {
                // Decrypt sensitive fields
                String decryptedEmail = decrypt(line[2]);
                String decryptedSalary = decrypt(line[3]);

                // Write decrypted data to output CSV
                String[] decryptedData = {line[0], line[1], decryptedEmail, decryptedSalary};
                writer.writeNext(decryptedData);
            }

            System.out.println("✅ Data decrypted and saved to CSV successfully.");
        } catch (Exception e) {
            System.out.println("❌ Error decrypting and reading CSV: " + e.getMessage());
        }
    }
}

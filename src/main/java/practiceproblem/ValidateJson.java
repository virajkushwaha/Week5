package practiceproblem;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateJson {
    public static void main(String[] args) {
        // Sample JSON string
        String jsonString = "{"
                + "\"name\": \"Viraj\","
                + "\"age\": 22,"
                + "\"subjects\": [\"Mathematics\", \"Computer Science\", \"Physics\"]"
                + "}";

        // Validate JSON structure
        if (isValidJson(jsonString)) {
            System.out.println("Valid JSON!");
        } else {
            System.out.println("Invalid JSON!");
        }
    }

    // Method to validate JSON
    public static boolean isValidJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString); // Parse JSON
            return jsonNode != null; // If parsing is successful, JSON is valid
        } catch (Exception e) {
            return false; // JSON is invalid if an exception occurs
        }
    }
}

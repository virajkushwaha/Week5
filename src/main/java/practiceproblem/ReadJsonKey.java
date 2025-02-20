package practiceproblem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadJsonKey {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(new File("src/main/java/practiceproblem/data.json"));

            // List to store extracted data
            List<String> extractedData = new ArrayList<>();

            // Iterate through each JSON object
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    String name = node.get("name").asText();
                    String email = node.get("email").asText();
                    extractedData.add("Name: " + name + ", Email: " + email);
                }
            }

            // Print extracted data
            extractedData.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

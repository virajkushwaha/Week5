package handsonproblem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;

public class MergeJsonFiles {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read both JSON files
            JsonNode json1 = objectMapper.readTree(new File("src/main/java/handsonproblem/file1.json"));
            JsonNode json2 = objectMapper.readTree(new File("src/main/java/handsonproblem/file2.json"));

            // Merge JSON objects
            ObjectNode mergedJson = objectMapper.createObjectNode();
            mergedJson.setAll((ObjectNode) json1);
            mergedJson.setAll((ObjectNode) json2);

            // Save merged JSON to a new file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/java/handsonproblem/merged.json"), mergedJson);

            // Print merged JSON
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

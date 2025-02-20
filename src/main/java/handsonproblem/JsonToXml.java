package handsonproblem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToXml {
    public static void main(String[] args) {
        try {
            // Read JSON file content
            String jsonString = new String(Files.readAllBytes(Paths.get("src/main/java/practiceproblem/data.json")));

            // Determine if JSON is an Object or Array
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String xmlData;

            if (jsonNode.isArray()) {
                // Convert JSON Array to XML
                JSONArray jsonArray = new JSONArray(jsonString);
                xmlData = XML.toString(jsonArray, "root");  // Wrap array inside <root>
            } else {
                // Convert JSON Object to XML
                JSONObject jsonObject = new JSONObject(jsonString);
                xmlData = XML.toString(jsonObject, "root");
            }

            // Save XML to a file
            Files.write(Paths.get("src/main/java/handsonproblem/data.xml"), xmlData.getBytes());

            // Print XML output
            System.out.println(xmlData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

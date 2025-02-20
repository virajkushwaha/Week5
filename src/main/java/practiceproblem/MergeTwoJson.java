package practiceproblem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MergeTwoJson {
    public static void main(String[] args) {
        // Creating first JSON object (student1)
        JSONObject student1 = new JSONObject();
        student1.put("Name", "Viraj");
        student1.put("age", 12);
        student1.put("email", "rajprince031@gmail.com");

        // Adding subjects as a JSON array
        List<String> subjects = Arrays.asList("Mathematics", "Computer Science", "Physics");
        student1.put("Subject", new JSONArray(subjects));

        // Creating second JSON object (student2)
        JSONObject student2 = new JSONObject();
        student2.put("class", 17);
        student2.put("phone", 705009337);

        // Merging both JSON objects into a final JSON object
        JSONObject finalStudentJson = new JSONObject(student1, JSONObject.getNames(student1)); // Copy student1 data
        for (String key : JSONObject.getNames(student2)) {
            finalStudentJson.put(key, student2.get(key)); // Copy student2 data
        }

        // Printing the merged JSON object
        System.out.println(finalStudentJson.toString(4)); // Pretty print
    }
}

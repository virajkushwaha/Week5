package practiceproblem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class CreateJsonObj {
    public static void main(String[] args) {
        // Creating a JSON object
        JSONObject student = new JSONObject();

        // Adding basic student details
        student.put("Name", "Viraj");
        student.put("age", 12);

        // Adding subjects as a JSON array
        List<String> subjects = Arrays.asList("Mathematics", "Computer Science", "Physics");
        student.put("Subject", new JSONArray(subjects));

        // Printing the formatted JSON
        System.out.println(student.toString(4));
    }
}

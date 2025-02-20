package practiceproblem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class ReadJsonKey {
    public static void main(String[] args) {
        // Creating a JSON object
        JSONObject student = new JSONObject();

        // Adding basic student details
        student.put("Name", "Viraj");
        student.put("age", 12);
        student.put("email","rajprince031@gmail.com");
        // Adding subjects as a JSON array
        List<String> subjects = Arrays.asList("Mathematics", "Computer Science", "Physics");
        student.put("Subject", new JSONArray(subjects));

        System.out.println("Name: " + student.getString("Name") );
        System.out.println("Email: "+ student.getString("email"));
    }
}

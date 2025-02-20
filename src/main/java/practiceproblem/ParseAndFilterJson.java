package practiceproblem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Student1 {
    private String name;
    private int age;
    private List<String> subjects;

    // Default constructor (Needed for Jackson)
    public Student1() {}

    // Parameterized constructor
    public Student1(String name, int age, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    //Getters (Needed for Jackson)
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getSubjects() { return subjects; }
}

public class ParseAndFilterJson {
    public static void main(String[] args) {
        try {
            // Sample JSON array as a string
            String jsonArray = "["
                    + "{\"name\":\"Viraj\", \"age\":22, \"subjects\":[\"Mathematics\", \"Computer Science\"]},"
                    + "{\"name\":\"Amit\", \"age\":27, \"subjects\":[\"Physics\", \"Chemistry\"]},"
                    + "{\"name\":\"Neha\", \"age\":30, \"subjects\":[\"Biology\", \"English\"]}"
                    + "]";

            // Convert JSON array to List of Student objects
            ObjectMapper objectMapper = new ObjectMapper();
            List<Student1> students = objectMapper.readValue(jsonArray, new TypeReference<List<Student1>>() {});

            // Filter students with age > 25
            List<Student1> filteredStudents = students.stream()
                    .filter(student -> student.getAge() > 25)
                    .collect(Collectors.toList());

            // Convert filtered list back to JSON
            String filteredJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredStudents);

            // Print filtered JSON
            System.out.println(filteredJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package practiceproblem;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

class Student {
    private String name;
    private int age;
    private List<String> subjects;

    // Constructor
    public Student(String name, int age, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    // Getters (Jackson needs these for serialization)
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getSubjects() { return subjects; }
}

public class ConvertListToJsonArray {
    public static void main(String[] args) {
        try {
            // Creating a list of Student objects
            List<Student> students = Arrays.asList(
                    new Student("Viraj", 22, Arrays.asList("Mathematics", "Computer Science")),
                    new Student("Rohan", 21, Arrays.asList("Physics", "Chemistry")),
                    new Student("Neha", 23, Arrays.asList("Biology", "English"))
            );

            // Converting list to JSON array
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);

            // Printing JSON array
            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package handsonproblem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Student2 {
    private int id;
    private String name;
    private int age;
    private String email;

    // Constructor
    public Student2(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters (needed for JSON serialization)
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
}

public class DatabaseToJsonReport {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/School";
        String user = "root";
        String password = "";

        List<Student2> students = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, age, email FROM students")) {

            // Fetch data and add to list
            while (rs.next()) {
                students.add(new Student2(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email")
                ));
            }

            // Convert list to JSON using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            String jsonReport = writer.writeValueAsString(students);

            // Print JSON report
            System.out.println(jsonReport);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

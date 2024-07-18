package student.studenthashmap.repo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import student.studenthashmap.model.Student;

@Repository
public class StudentRepository {
    private final Map<Integer, Student> studentMap;

    public StudentRepository() {
        studentMap = new HashMap<>();
 
        studentMap.put(1, new Student(1, "Rate", "rate@gmail.com", 400, "Delhi", 
                        "Male", LocalDate.parse("20000708", DateTimeFormatter.BASIC_ISO_DATE), "100034000460"));
        studentMap.put(2, new Student(2, "Gain", "gain@gmail.com", 378, "Noida", 
                        "Female", LocalDate.parse("20000509", DateTimeFormatter.BASIC_ISO_DATE), "100400000000"));
        studentMap.put(3, new Student(3, "Gate", "gate@gmail.com", 390, "Jaipur", 
                        "Others", LocalDate.parse("20000319", DateTimeFormatter.BASIC_ISO_DATE), "120000000000"));
    }

    public Map<Integer, Student> getAllStudents() {

        boolean ans = studentMap.isEmpty();
        if (ans == true) {
            throw new IllegalArgumentException("No Student Data available.");
        }
        else {
            return new HashMap<>(studentMap);
        }        
    }
    
    public Optional<Student> findByRollNo(int rollNo) {
        return Optional.ofNullable(studentMap.get(rollNo));
    }

    public List<Student> search(String name) {
        return studentMap.values().stream()
                         .filter(student -> student.getName().startsWith(name))
                         .collect(Collectors.toList());
    }

    public Student save(Student student) {
        if (studentMap.containsKey(student.getRollNo())) {
            throw new IllegalArgumentException("Student with this roll number already exists");
        }
        studentMap.put(student.getRollNo(), student);
        return student;
    }

    public boolean delete(Integer rollNo) {
        return studentMap.remove(rollNo) != null;
    }

    public Optional<Student> update(Student student) {
        if (!studentMap.containsKey(student.getRollNo())) {
            return Optional.empty();
        }
        studentMap.put(student.getRollNo(), student);
        return Optional.of(student);
    }
}

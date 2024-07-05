package student.studenthashmap.repo;

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
        studentMap.put(1, new Student(1, "Rate", "rate@gmail.com", 400, "Delhi", "Male" ));
        studentMap.put(2, new Student(2, "Gain", "gain@gmail.com", 378, "Noida", "Female"));
        studentMap.put(3, new Student(3, "Gate", "gate@gmail.com", 390, "Jaipur", "Male"));
    }

    public Map<Integer, Student> getAllStudents() {
        return new HashMap<>(studentMap);
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

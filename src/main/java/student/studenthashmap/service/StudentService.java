package student.studenthashmap.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import student.studenthashmap.model.Student;
import student.studenthashmap.repo.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public Map<Integer, Student> getStudents() {
        return repository.getAllStudents();
    }

    public Optional<Student> getStudentByRollNo(int rollNo) {
        return repository.findByRollNo(rollNo);
    }

    public String deleteStudent(int rollNo) {
        boolean isDeleted = repository.delete(rollNo);
        return isDeleted ? "Student removed !! " + rollNo : "Student not found with id !! " + rollNo;
    }

    public Optional<Student> updateStudent(Student student) {
        return repository.update(student);
    }

    public Student updateStudentField(int rollNo, Map<String, Object> fields) {
        Optional<Student> studentOpt = repository.findByRollNo(rollNo);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            fields.forEach((fieldName, fieldValue) -> {
                Field field = ReflectionUtils.findField(Student.class, fieldName);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, student, fieldValue);
                }
            });
            return repository.update(student).orElseThrow(() -> new IllegalArgumentException("Student not found with roll number: " + rollNo));
        } else {
            throw new IllegalArgumentException("Student not found with roll number: " + rollNo);
        }
    }
}

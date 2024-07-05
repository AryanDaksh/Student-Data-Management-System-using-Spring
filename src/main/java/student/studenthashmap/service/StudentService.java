package student.studenthashmap.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

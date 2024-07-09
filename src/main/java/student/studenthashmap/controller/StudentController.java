package student.studenthashmap.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import student.studenthashmap.model.Student;
import student.studenthashmap.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student addStudent(@RequestBody @Valid Student student) {
        return service.saveStudent(student);
    }

    @GetMapping("/all")
    public Map<Integer, Student> findAllStudents() {
        return service.getStudents();
    }

    @GetMapping("{rollNo}")
    public Optional<Student> findStudentByRollNo(@PathVariable @Valid int rollNo) {
        return service.getStudentByRollNo(rollNo);
    }

    @PutMapping
    public Optional<Student> updateStudent(@RequestBody @Valid Student student) {
        return service.updateStudent(student);
    }

    @PatchMapping("{rollNo}")
    public Student updateStudentField (@PathVariable int rollNo,@Valid @RequestBody Map<String, Object> fields) {
        return service.updateStudentField(rollNo, fields);
    }
 
    @DeleteMapping("{rollNo}")
    public String deleteStudent(@PathVariable int rollNo) {
        return service.deleteStudent(rollNo);
    }
}

package student.studenthashmap.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import student.studenthashmap.model.*;
import student.studenthashmap.repo.StudentRepository;
import student.studenthashmap.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student addStudent(@RequestBody @Valid Student student) {
        return service.saveStudent(student);
    }

    @GetMapping("/v1/all")
    public Map<Integer, Student> findAllStudents() {
        return service.getStudents();
    }

    @GetMapping("/v2/all")
    public Map<Integer, StudentDTO> findAllStudentsDTO() {
        return service.getStudentsDTO();
    }

    @GetMapping("/v1/{rollNo}")
    public Optional<Student> findStudentByRollNo(@PathVariable @Valid int rollNo) {
        return service.getStudentByRollNo(rollNo);
    }

    @GetMapping("/v2/{rollNo}")
    public Optional<StudentDTO> findStudentByRollNoDTO(@PathVariable @Valid int rollNo) {
        return service.getStudentByRollNoDTO(rollNo);
    }

    @PutMapping
    public Optional<Student> updateStudent(@RequestBody @Valid Student student) {
        return service.updateStudent(student);
    }

    @PatchMapping("{rollNo}")
    public Student updateStudentField(@PathVariable int rollNo, @Valid @RequestBody Map<String, Object> fields) {
        return service.updateStudentField(rollNo, fields);
    }

    @DeleteMapping("/{rollNo}")
    public String deleteStudent(@PathVariable int rollNo) {
        return service.deleteStudent(rollNo);
    }

    public ModelAndView save(@ModelAttribute StudentRepository studentMap)  {    
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("student-data");        
    modelAndView.addObject("student", studentMap);      
    return modelAndView;    
    }
        
}

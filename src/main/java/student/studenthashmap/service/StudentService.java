package student.studenthashmap.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import student.studenthashmap.mapper.StudentMapper;
import student.studenthashmap.model.Student;
import student.studenthashmap.model.StudentDTO;
import student.studenthashmap.repo.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public StudentDTO saveStudentDTO(StudentDTO studentDTO) {
        Student student = mapper.studentDTOToStudent(studentDTO);
        Student savedStudent = repository.save(student);
        return mapper.studentToStudentDTO(savedStudent);
    }

    public Map<Integer, Student> getStudents() {
        return repository.getAllStudents();
    }

    public Map<Integer, StudentDTO> getStudentsDTO() {
        return repository.getAllStudents().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> mapper.studentToStudentDTO(entry.getValue())
                ));
    }

    public Optional<Student> getStudentByRollNo(int rollNo) {
        return repository.findByRollNo(rollNo);
    }

    public Optional<StudentDTO> getStudentByRollNoDTO(int rollNo) {
        return repository.findByRollNo(rollNo)
                .map(mapper::studentToStudentDTO);
    }

    public String deleteStudent(int rollNo) {
        boolean isDeleted = repository.delete(rollNo);
        return isDeleted ? "Student removed. " + rollNo : "Student not found with roll no. " + rollNo;
    }

    public Optional<Student> updateStudent(Student student) {
        return repository.update(student);
    }

    public Optional<StudentDTO> updateStudentDTO(StudentDTO studentDTO) {
        Student student = mapper.studentDTOToStudent(studentDTO);
        return repository.update(student).map(mapper::studentToStudentDTO);
    }

    public Student updateStudentField(int rollNo, Map<String, Object> fields) {
        Optional<Student> studentOpt = repository.findByRollNo(rollNo);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            fields.forEach((fieldName, fieldValue) -> {
                Field field = ReflectionUtils.findField(Student.class, fieldName);
                if (field != null) {
                    field.setAccessible(true);
                    if ("birthDate".equals(fieldName) && fieldValue instanceof String) {
                        LocalDate birthDate = LocalDate.parse((String) fieldValue, DateTimeFormatter.ISO_LOCAL_DATE);
                        ReflectionUtils.setField(field, student, birthDate);
                    } else {
                        ReflectionUtils.setField(field, student, fieldValue);
                    }
                }
            });
            return repository.update(student).orElseThrow(() -> new IllegalArgumentException("Student not found with roll number: " + rollNo));
        } else {
            throw new IllegalArgumentException("Student not found with roll number: " + rollNo);
        }
    }
}

package student.studenthashmap.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.validation.*;
import student.studenthashmap.mapper.StudentMapper;
import student.studenthashmap.model.*;
import student.studenthashmap.repo.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    private final Validator validator;

    @PostConstruct
    public void setUp() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new Config());
        objectMapper.registerModule(module);
    }

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
        return isDeleted ? "Removed student with Roll Number - " + rollNo : "No studentfound with Roll Number -  " + rollNo;
    }

    public Optional<Student> updateStudent(Student student) {
        return repository.update(student);
    }

    public Optional<StudentDTO> updateStudentDTO(StudentDTO studentDTO) {
        Student student = mapper.studentDTOToStudent(studentDTO);
        return repository.update(student).map(mapper::studentToStudentDTO);
    }

    List<DateTimeFormatter> dateFormats = Arrays.asList(
        DateTimeFormatter.ISO_LOCAL_DATE
    );

    public Student updateStudentField(@Valid int rollNo, Map<String, Object> fields) {
        Optional<Student> studentOpt = repository.findByRollNo(rollNo);

        if (studentOpt.isPresent()) {
        Student student = studentOpt.get();

        fields.forEach((fieldName, fieldValue) -> {
            try {
                Field field = Student.class.getDeclaredField(fieldName);
                field.setAccessible(true);

                if ("birthDate".equals(fieldName) && fieldValue instanceof String) {
                    LocalDate birthDate = parseDate((String) fieldValue, dateFormats);
                    field.set(student, birthDate);
                } else {
                    field.set(student, fieldValue);
                }

                validateField(student, fieldName, fieldValue);

            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Unknown field: " + fieldName);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + fieldName, e);
            }
        });

        return repository.update(student).orElseThrow(() -> new IllegalArgumentException("Student not found with roll number: " + rollNo));
        } else {
            throw new IllegalArgumentException("Student not found with roll number: " + rollNo);
        }

    }

    private LocalDate parseDate(String dateStr, List<DateTimeFormatter> dateFormats) {
        for (DateTimeFormatter formatter : dateFormats) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("Unable to parse date: " + dateStr + ". Please use the YYYY-MM-DD format.", dateStr, 0);
            }
        }
        throw new DateTimeParseException("Unable to parse date: " + dateStr + "Please use the YYYY-MM-DD format.", dateStr, 0);
    }

    public StudentService() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private void validateField(Student student, String fieldName, Object fieldValue) {
        Set<ConstraintViolation<Student>> violations = validator.validateProperty(student, fieldName);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

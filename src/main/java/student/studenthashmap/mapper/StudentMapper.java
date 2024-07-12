package student.studenthashmap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import student.studenthashmap.model.Student;
import student.studenthashmap.model.StudentDTO;

@Mapper
public interface StudentMapper {

    @Mapping(target = "studentRollNumber", source = "rollNo")
    @Mapping(target = "studentName", source = "name")
    @Mapping(target = "studentEmail", source = "email")
    @Mapping(target = "studentMarks", source = "marks")
    @Mapping(target = "studentGender", source = "gender")
    @Mapping(target = "studentBirthDate", source = "birthDate", dateFormat = "yyyy-MM-dd")
    StudentDTO studentToStudentDTO(Student entity);

    @Mapping(target = "rollNo", source = "studentRollNumber")
    @Mapping(target = "name", source = "studentName")
    @Mapping(target = "email", source = "studentEmail")
    @Mapping(target = "marks", source = "studentMarks")
    //@Mapping(target = "location", source = "studentLocation")
    @Mapping(target = "gender", source = "studentGender")
    @Mapping(target = "birthDate", source = "studentBirthDate", dateFormat = "yyyy-MM-dd")
    //@Mapping(target = "aadharNo", source = "studentAadharno")
    
    static Student studentDTOToStudent(StudentDTO dto) {
        throw new UnsupportedOperationException("Unimplemented method 'studentDTOToStudent'");
    }

    //ModelMapper modelMapper = new ModelMapper(),
    //StudentDTO = modelMapper.map(null, StudentDTO.class);
}
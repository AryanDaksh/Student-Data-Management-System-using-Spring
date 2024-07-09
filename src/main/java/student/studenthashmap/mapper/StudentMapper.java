package student.studenthashmap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import student.studenthashmap.model.Student;
import student.studenthashmap.model.StudentDTO;

@Mapper
public interface StudentMapper {

    @Mapping(target = "studentRollNumber", source = "entity.rollNo")
    @Mapping(target = "studentName", source = "entity.name")
    @Mapping(target = "studentEmail", source = "entity.email")
    @Mapping(target = "studentMarks", source = "entity.marks")
    @Mapping(target = "studentLocation", source = "entity.location")
    @Mapping(target = "studentGender", source = "entity.gender")

    StudentDTO studentToStudentDTO(Student entity);

    @Mapping(target = "rollNo", source = "dto.studentRollNumber")
    @Mapping(target = "name", source = "dto.studentName")
    @Mapping(target = "email", source = "dto.studentEmail")
    @Mapping(target = "marks", source = "dto.studentMarks")
    @Mapping(target = "location", source = "dto.studentLocation")
    @Mapping(target = "gender", source = "dto.studentGender")
    static

    Student studentDTOToStudent(StudentDTO dto) {
        throw new UnsupportedOperationException("Unimplemented method 'studentDTOToStudent'");
    }

}

package student.studenthashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import student.studenthashmap.mapper.StudentMapper;
import student.studenthashmap.model.Student;
import student.studenthashmap.model.StudentDTO;

public class MapperTest {

    @Test
    public void studentDTOtoStudent(){
        StudentDTO dto = new StudentDTO();
    dto.setStudentRollNumber(4);
    dto.setStudentName("John");

    Student entity = StudentMapper.studentDTOToStudent(dto);

    assertEquals(dto.getStudentRollNumber(), entity.getRollNo());
    assertEquals(dto.getStudentName(), entity.getName());
    }

}

package student.studenthashmap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import student.studenthashmap.controller.StudentController;

@SpringBootTest
public class StudentControllerTest {

    @Autowired
    private StudentController controller;

    @Test
    void contextLoads() throws Exception {
		        assertThat(controller).isNotNull(); 
    }
}

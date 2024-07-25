package student.studenthashmap.model;

import java.time.LocalDate;

public class StudentDTO {

    private int studentRollNumber;

    private String studentName;

    private String studentEmail;

    private int studentMarks;

    private String studentGender;

    private LocalDate studentBirthDate;

    public int getStudentRollNumber() {
        return studentRollNumber;
    }
    public void setStudentRollNumber(int studentRollNumber) {
        this.studentRollNumber = studentRollNumber;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentEmail() {
        return studentEmail;
    }
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    public int getStudentMarks() {
        return studentMarks;
    }
    public void setStudentMarks(int studentMarks) {
        this.studentMarks = studentMarks;
    }
    public String getStudentGender() {
        return studentGender;
    }
    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
    public LocalDate getStudentBirthDate() {
        return studentBirthDate;
    }
    public void setStudentBirthDate(LocalDate studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

}
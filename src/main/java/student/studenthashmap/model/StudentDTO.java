package student.studenthashmap.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import student.studenthashmap.validation.AgeLimit;

public class StudentDTO {

    @NotNull(message = "Roll Number is a mandatory field.")
    @Min(value = 1)
    @Max(value = 60)
    private int studentRollNumber;

    @NotBlank(message = "Name is a mandatory field.")
    @Size(min = 3, message = "Name should be of at least 3 letters")
    @Size(max = 10, message = "Name should be of 10 letters or less.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
            message = "Name should not contain any special characters.")
    private String studentName;

    @NotBlank(message = "Email is a mandatory field.")
    @Email(message = "Enter a valid Email Id.")
    private String studentEmail;

    @NotNull(message = "Marks is a mandatory field.")
    @Min(0)
    @Max(500)
    @Digits(integer = 3, fraction = 0, message = "Enter marks in format 000.")
    private int studentMarks;

    @NotBlank(message = "Location is a mandatory field.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
            message = "Location must not contain any special characters.")
    private String studentLocation;

    @NotBlank(message = "Gender is a mandatory field.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
            message = "Gender must not contain any special characters.")
    private String studentGender;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birth Date must be in the format YYYY-MM-DD.")
    @AgeLimit(minimumAge=18, message="Student should be atleast 18 years old")
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

    public String getStudentLocation() {
        return studentLocation;
    }

    public void setStudentLocation(String studentLocation) {
        this.studentLocation = studentLocation;
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
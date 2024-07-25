package student.studenthashmap.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import student.studenthashmap.validation.AgeLimit;

public class Student {

    @NotNull(message= "Roll Number is a mandatory field.")
    @Min(value=1)
    @Max(value=100)
    private int rollNo;

    @NotBlank(message= "Name is a mandatory field.")
    @Size(min=3, message="Name should be of atleast 3 letters")
    @Size(max=10, message = "Name can be of maximum 10 letters.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
            message = "Name should not contain any special characters.")
    private String name;

    @NotBlank(message= "Email is a mandatory field.")
    @Email(message="Enter a valid Email Id.")
    private String email;

    @NotNull(message= "Marks is a mandatory field.")
    @Min(0)
    @Max(500)
    @Digits(integer = 3, fraction = 0, message="Marks should be between 0 and 500.")
    private int marks;

    @NotBlank(message = "Location is a mandatory field.")
    @Size(min=3, message="Location should be of atleast 3 letters")
    @Size(max=10, message = "Location can be of maximum 10 letters.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
    message = "Location must not contain any special characters.")
    private String location;

    @NotBlank(message = "Gender is a mandatory field.")
    @Pattern(regexp = "(?:Male|Female|Others)$",
    message = "Gender should be one of the following: Male, Female, Others.")
    private String gender;

    @AgeLimit(minimumAge=18, message="Student should be atleast 18 years old.")
    @JsonFormat
    private LocalDate birthDate;

    @NotBlank(message = "Aadhar Number is a mandatory field.")
    @Pattern(regexp = "[0-9]{12}", message = "Enter a valid 12-digit Aadhar Number.")
    private String aadharNo;

    public Student(int rollNo, String name, String email, int marks, String location, String gender, LocalDate birthDate, String aadharNo) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.marks = marks;
        this.location = location;
        this.gender = gender;
        this.birthDate = birthDate;
        this.aadharNo = aadharNo;
    }
    
    public Student() {
    }
    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getAadharNo() {
        return aadharNo;
    }
    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

}
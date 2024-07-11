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

public class Student {

    @NotNull(message= "Roll Number is a mandatory field.")
    @Min(value=1)
    @Max(value=60)
    private int rollNo;

    @NotBlank(message= "Name is a mandatory field.")
    @Size(min=3, message="Name should be of atleast 3 letters")
    @Size(max=10, message = "Name should be of 10 letters or less.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
            message = "Name should not contain any special characters.")
    private String name;

    @NotBlank(message= "Email is a mandatory field.")
    @Email(message="Enter a valid Email Id.")
    private String email;

    @NotNull(message= "Marks is a mandatory field.")
    @Min(0)
    @Max(500)
    @Digits(integer = 3, fraction = 0, message="Enter marks in format 000.")
    private int marks;

    @NotBlank(message = "Location is a mandatory field.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
    message = "Location must not contain any special characters.")
    private String location;

    @NotBlank(message = "Gender is a mandatory field.")
    @Pattern(regexp = "^[a-zA-Z]{0,10}$",
    message = "Gender must not contain any special characters.")
    private String gender;

    @AgeLimit(minimumAge=18, message="Student should be atleast 18 years old")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birth date must be in the format YYYY-MM-DD.")
    private LocalDate birthDate;

    public Student(int rollNo, String name, String email, int marks, String location, String gender, LocalDate birthDate) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.marks = marks;
        this.location = location;
        this.gender = gender;
        this.birthDate = birthDate;
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

    
    @Override
    public String toString() {
        return "Student [rollNo=" + rollNo + ", name=" + name + ", email=" + email + ", marks=" + marks + ", location=" + location
                + ", gender=" + gender + ", getEmail()=" + getEmail() + ", getMarks()=" + getMarks() + ", getName()=" + getName()
                + ", getRollNo()=" + getRollNo() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
                + ", toString()=" + super.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (rollNo != other.rollNo)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return marks == other.marks;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rollNo;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + marks;
        return result;
    }
}
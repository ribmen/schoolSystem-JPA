package com.victor.JPAapplication.repository;

import com.victor.JPAapplication.entity.Guardian;
import com.victor.JPAapplication.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.String;
import java.util.List;


@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("yasmim@gmail.com")
                .firstName("Yasmim")
                .lastName("Menezes")
                //.guardianName("Iris")
                //.guardianEmail("iris@gmail.com")
                //.guardianMobile("84999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .email("mama@gmail.com")
                .name("Mama")
                .mobile("123456789")
                .build();

        Student student = Student.builder()
                .firstName("Carlo")
                .emailId("carlo@mexico.com")
                .lastName("Fascinetto")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students =
                studentRepository.findByFirstName("Victor");
        System.out.println("students = " + students);

    }
    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students =
                studentRepository.findByFirstNameContaining("Vic");
        System.out.println("students = " + students);

    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> students =
                studentRepository.findByGuardianName("Iris");
        System.out.println("Students = " + students);
    }

    @Test
    public void printgetStudentByEmailAddress() {
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "carlo@mexico.com"
                );
        System.out.println("student = " + student); //soutv
    }

    @Test
    public void printgetStudentFirstNameByEmailAddress() {
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress(
                        "victor@gmail.com"
                );
        System.out.println("firstName = " + firstName);
    }

    @Test
    public void printgetStudentByEmailAddressNative() {
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "victor@gmail.com"
                );
        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam() {
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam(
                        "yasmim@gmail.com"
                );
        System.out.println("student = " + student);
    }
    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "Victor Gabriel",
                "victor@gmail.com"
                );
    }
}
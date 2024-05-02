package com.victor.JPAapplication.repository;

import com.victor.JPAapplication.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                .guardianName("Iris")
                .guardianEmail("iris@gmail.com")
                .guardianMobile("84999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }
}
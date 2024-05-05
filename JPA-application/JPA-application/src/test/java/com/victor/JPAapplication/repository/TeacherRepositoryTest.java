package com.victor.JPAapplication.repository;

import com.victor.JPAapplication.entity.Course;
import com.victor.JPAapplication.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course courseFWJS = Course.builder()
                .title("DIMAP")
                .credit(7)
                .build();

        Course courseEDB2 = Course.builder()
                .title("EDB2")
                .credit(5)
                .build();

        Course courseTYPO = Course.builder()
                .title("DEART")
                .credit(4)
                .build();
        Teacher teacher =
                Teacher.builder()
                        .firstName("Tonhão ")
                        .lastName("de ME")
                        //.courses(List.of(courseFWJS, courseEDB2, courseTYPO ))
                        .build();

        teacherRepository.save(teacher);
    }

}
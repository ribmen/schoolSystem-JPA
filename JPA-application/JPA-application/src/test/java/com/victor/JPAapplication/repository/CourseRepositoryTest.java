package com.victor.JPAapplication.repository;

import com.victor.JPAapplication.entity.Course;
import com.victor.JPAapplication.entity.Student;
import com.victor.JPAapplication.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses =
                courseRepository.findAll();
        System.out.println("courses = " + courses);

    }

    @Test
    public void saveCourseWithTeacherObject() {
//a new teacher and a new course should be created and the foreign key for
        //teacher and course should be the same
        Teacher teacher = Teacher.builder()
                .firstName("Fred")
                .lastName("Lopes")
                .build();

        Course course = Course
                .builder()
                .title("Java")
                .credit(10)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPagewithThreeRecords =
                PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(firstPagewithThreeRecords)
                        .getContent();

        long totalElements =
                courseRepository.findAll(firstPagewithThreeRecords)
                                .getTotalElements();
        long totalPages =
                courseRepository.findAll(firstPagewithThreeRecords)
                        .getTotalElements();

        System.out.println("totalPages = " + totalPages);

        System.out.println("totalElements = " + totalElements);

        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting() { //by title
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                         Sort.by("title")
                );
        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit").descending()
                );
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                                .descending()
                                .and(Sort.by("credit"))
                );
        List<Course> courses
                = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printfindByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);
        List<Course> courses =
                courseRepository.findByTitleContaining(
                        "D",
                        firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Heitor")
                .lastName("do nPITI")
                .build();

        Student student = Student.builder()
                .firstName("Izabelle")
                .lastName("Fernandes")
                .emailId("bella@gmail.com")
                .build();
        Course course = Course
                .builder()
                .title("BD")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}
package com.victor.JPAapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private int credit;

    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id", //database that we want to define
                    referencedColumnName = "courseId" //class property already defined
            ),
            inverseJoinColumns =  @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    ) //we are creating a new table that will have two columns, course_id and student_id
    //the new table contains the mapping between the two already
    //created tables
    private List<Student> students;

    public void addStudents(Student student) {
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
}

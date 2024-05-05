package com.victor.JPAapplication.repository;

import com.victor.JPAapplication.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameContaining(String name);
    List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1") //?1 = FIRST PARAMETER
    Student getStudentByEmailAddress(String emailId);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1") //?1 = FIRST PARAMETER
    String getStudentFirstNameByEmailAddress(String emailId);

    //NATIVE QUERY
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1", //? question mark
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //NATIVE Named Param
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :emailId", //? question mark
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailId") String emailId
    );

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address= ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);


}

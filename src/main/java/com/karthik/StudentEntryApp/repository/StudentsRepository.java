package com.karthik.StudentEntryApp.repository;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Long> {

    @Query(value = "select * from STUDENTS_ENTITY where id = :id order by id", nativeQuery = true)
    public Optional<StudentsEntity> fetchStudentsById(Long id);

    @Query(value = "select * from STUDENTS_ENTITY where firstname = :firstname order by id", nativeQuery = true)
    public List<StudentsEntity> fetchStudentsByFirstName(@Param("firstname") String firstname);

    @Query(value = "select * from STUDENTS_ENTITY where lastname = :lastname order by id", nativeQuery = true)
    public List<StudentsEntity> fetchStudentsByLastName(@Param("lastname") String lastname);

    @Query(value = "select s from StudentsEntity s where s.email = :email order by id")
    public List<StudentsEntity> fetchStudentsByEmail(String email);

    @Query(value = "select * from STUDENTS_ENTITY where gender = :gender", nativeQuery = true)
    public List<StudentsEntity> fetchStudentsByGender(String gender);

}

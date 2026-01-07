package com.karthik.StudentEntryApp.repository;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Long> {

    @Query(value = "select * from STUDENTS_ENTITY where name = :name", nativeQuery = true)
    public List<StudentsEntity> fetchStudentsByName(String name);

}

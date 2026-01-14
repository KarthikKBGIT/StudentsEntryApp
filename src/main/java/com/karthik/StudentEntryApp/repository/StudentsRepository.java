package com.karthik.StudentEntryApp.repository;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Long> {

    @Query(value = "select * from STUDENTS_ENTITY where name = :name order by id", nativeQuery = true)
    public List<StudentsEntity> fetchStudentsByName(@Param("name") String name);

    @Query(value = "select s from StudentsEntity s where s.department = :department order by s.id")
    public List<StudentsEntity> fetchStudentsByDepartment(@Param("department") String department);

    @Query(value = "select * from STUDENTS_ENTITY where state = :state order by id", nativeQuery = true)
    public List<StudentsEntity> fetchStudentsByState(@Param("state") String state);

}

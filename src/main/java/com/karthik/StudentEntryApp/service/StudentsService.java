package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentsService {

    public StudentsEntity saveStudent(StudentsEntity studentsEntity);

    public List<StudentsEntity> fetchAllStudents();

    public Page<StudentsEntity> fetchStudentsWithPagination(int page, int size);

    public StudentsEntity fetchStudentById(Long id) throws StudentNotFound;

    public List<StudentsEntity> fetchStudentsByFirstName(String first_name) throws StudentNameNotFound, StudentNotFound;

    public List<StudentsEntity> fetchStudentsByLastName(String last_name) throws StudentNameNotFound, StudentNotFound;

    public List<StudentsEntity> fetchByEmailId(String email) throws StudentEmailNotFound, StudentNotFound;

    public List<StudentsEntity> fetchByGender(String gender) throws InvalidGender, StudentNotFound;

    public void deleteStudentById(Long id) throws StudentIDNotFound, StudentNotFound;

    public StudentsEntity updateStudentById(Long id, StudentsEntity studentsEntity) throws StudentIDNotFound, StudentNotFound, StudentNameNotFound;

}
package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.StudentIDNotFound;
import com.karthik.StudentEntryApp.error.StudentNameNotFound;

import java.util.List;

public interface StudentsService {

    public StudentsEntity saveStudent(StudentsEntity studentsEntity);

    public List<StudentsEntity> fetchAllStudents();

    public StudentsEntity fetchStudentById(Long id) throws StudentIDNotFound;

    public List<StudentsEntity> fetchStudentsByName(String name) throws StudentNameNotFound;

    public void deleteStudentById(Long id);

    public StudentsEntity updateStudentById(Long id, StudentsEntity studentsEntity);

}
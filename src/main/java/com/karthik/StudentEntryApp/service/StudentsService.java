package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.StudentDepartmentNotFound;
import com.karthik.StudentEntryApp.error.StudentIDNotFound;
import com.karthik.StudentEntryApp.error.StudentNameNotFound;
import com.karthik.StudentEntryApp.error.StudentStateNotFound;

import java.util.List;

public interface StudentsService {

    public StudentsEntity saveStudent(StudentsEntity studentsEntity);

    public List<StudentsEntity> fetchAllStudents();

    public StudentsEntity fetchStudentById(Long id) throws StudentIDNotFound;

    public List<StudentsEntity> fetchStudentsByName(String name) throws StudentNameNotFound;

    public List<StudentsEntity> fetchStudentsByDepartment(String departmentName) throws StudentNameNotFound, StudentDepartmentNotFound;

    public List<StudentsEntity> fetchStudentsByState(String state) throws StudentStateNotFound;

    public void deleteStudentById(Long id) throws StudentIDNotFound;

    public StudentsEntity updateStudentById(Long id, StudentsEntity studentsEntity) throws StudentIDNotFound;

}
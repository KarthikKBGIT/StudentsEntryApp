package com.karthik.StudentEntryApp.controller;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.StudentIDNotFound;
import com.karthik.StudentEntryApp.service.StudentsDepartmentImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    StudentsDepartmentImpl studentsService;

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public StudentsEntity saveStudent(@Valid @RequestBody StudentsEntity studentsEntity) {
        return studentsService.saveStudent(studentsEntity);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<StudentsEntity> fetchAllStudents() {
        return studentsService.fetchAllStudents();
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public StudentsEntity fetchStudentById(@PathVariable("id") Long id) throws StudentIDNotFound {
        return studentsService.fetchStudentById(id);
    }

    @RequestMapping(value = "/students/name/('{name}')", method = RequestMethod.GET)
    public List<StudentsEntity> fetchStudentsByName(String name){
        return studentsService.fetchStudentsByName(name);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public String deleteStudentById(@PathVariable("id") Long id){
        studentsService.deleteStudentById(id);
        return "Student id = " + id + " deleted successfully!";
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public StudentsEntity updateStudentById(@PathVariable("id") Long id,
                                            @RequestBody StudentsEntity studentsEntity){
        return studentsService.updateStudentById(id, studentsEntity);
    }
}

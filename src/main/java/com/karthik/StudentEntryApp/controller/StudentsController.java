package com.karthik.StudentEntryApp.controller;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.StudentIDNotFound;
import com.karthik.StudentEntryApp.error.StudentNameNotFound;
import com.karthik.StudentEntryApp.service.StudentsDepartmentImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
public class StudentsController {

    @Autowired
    StudentsDepartmentImpl studentsService;

    Logger logger = Logger.getLogger(StudentsController.class.getName());

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public StudentsEntity saveStudent(@Valid @RequestBody StudentsEntity studentsEntity) {
        logger.info("Received request to save student: " + studentsEntity);
        return studentsService.saveStudent(studentsEntity);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<StudentsEntity> fetchAllStudents() {
        logger.info("Received request to fetch all students");
        return studentsService.fetchAllStudents();
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public StudentsEntity fetchStudentById(@PathVariable("id") Long id) throws StudentIDNotFound {
        logger.info("Received request to fetch student by ID: " + id);
        return studentsService.fetchStudentById(id);
    }

    @RequestMapping(value = "/students/name/('{name}')", method = RequestMethod.GET)
    public List<StudentsEntity> fetchStudentsByName(@PathVariable("name") String name) throws StudentNameNotFound {
        logger.info("Received request to fetch students by name: " + name);
        return studentsService.fetchStudentsByName(name);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public String deleteStudentById(@PathVariable("id") Long id) throws StudentIDNotFound {
        logger.info("Received request to delete student by ID: " + id);
        studentsService.deleteStudentById(id);
        return "Student with ID: " + id + " has been deleted successfully.";
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public StudentsEntity updateStudentById(@PathVariable("id") Long id,
                                            @RequestBody StudentsEntity studentsEntity) throws StudentIDNotFound {
        logger.info("Received request to update student by ID: " + id + " with data: " + studentsEntity);
        return studentsService.updateStudentById(id, studentsEntity);
    }
}

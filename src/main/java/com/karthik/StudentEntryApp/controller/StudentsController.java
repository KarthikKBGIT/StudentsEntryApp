package com.karthik.StudentEntryApp.controller;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.StudentIDNotFound;
import com.karthik.StudentEntryApp.error.StudentNameNotFound;
import com.karthik.StudentEntryApp.error.StudentNotFound;
import com.karthik.StudentEntryApp.service.StudentsDepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StudentsController {

    @Autowired
    StudentsDepartmentService studentsService;

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public StudentsEntity saveStudent(@Valid @RequestBody StudentsEntity studentsEntity) {
        log.info("Received request to save student: " + studentsEntity);
        return studentsService.saveStudent(studentsEntity);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<StudentsEntity> fetchAllStudents(@RequestParam(name = "firstname", required = false) String firstName,
                                                 @RequestParam(name = "lastname", required = false) String lastName) throws StudentNameNotFound, StudentNotFound {
        log.info("Received request to fetch students");
        if(firstName==null && lastName==null){
            log.info("First name and last name are both empty");
            log.info("Fetching all students details");
            return studentsService.fetchAllStudents();
        }
        else if(lastName==null && !firstName.isEmpty()){
            log.info("Fetching all students details for the first name: " + firstName);
            return studentsService.fetchStudentsByFirstName(firstName);
        }
        else if(firstName==null && !lastName.isEmpty()){
            log.info("Fetching all students details for the last name: " + lastName);
            return studentsService.fetchStudentsByLastName(lastName);
        }
        else{
            log.info("Invalid request parameters - Both first name and last name cannot be provided together");
            throw new StudentNameNotFound("Invalid request parameters - Both first name and last name cannot be provided together");
        }
    }

    @RequestMapping(value = "/students/page", method = RequestMethod.GET)
    public Page<StudentsEntity> fetchAllStudentsWithPagination(@RequestParam int page, @RequestParam int size) {
        log.info("Received request to fetch students with pagination - Page: " + page + ", Size: " + size);
        return studentsService.fetchStudentsWithPagination(page, size);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public StudentsEntity fetchStudentById(@PathVariable("id") Long id) throws StudentNotFound {
        log.info("Received request to fetch student by ID: " + id);
        return studentsService.fetchStudentById(id);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public String deleteStudentById(@PathVariable("id") Long id) throws StudentIDNotFound {
        log.info("Received request to delete student by ID: " + id);
        studentsService.deleteStudentById(id);
        return "Student with ID: " + id + " has been deleted successfully.";
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    public StudentsEntity updateStudentById(@PathVariable("id") Long id,
                                            @RequestBody StudentsEntity studentsEntity) throws StudentIDNotFound, StudentNotFound, StudentNameNotFound {
        log.info("Received request to update student by ID: " + id + " with data: " + studentsEntity);
        return studentsService.updateStudentById(id, studentsEntity);
    }

    //This is method is only for
    @RequestMapping(value = "/csrf-token", method = RequestMethod.GET)
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }
}

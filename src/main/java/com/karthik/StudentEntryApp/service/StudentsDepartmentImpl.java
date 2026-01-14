package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.controller.StudentsController;
import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.StudentIDNotFound;
import com.karthik.StudentEntryApp.error.StudentNameNotFound;
import com.karthik.StudentEntryApp.repository.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Slf4j
@Service
public class StudentsDepartmentImpl implements StudentsService{
    @Autowired
    private StudentsRepository studentsRepository;

    Logger logger = Logger.getLogger(StudentsController.class.getName());

    @Override
    public StudentsEntity saveStudent(StudentsEntity studentsEntity) {
        return studentsRepository.save(studentsEntity);
    }

    @Override
    public List<StudentsEntity> fetchAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public StudentsEntity fetchStudentById(Long id) throws StudentIDNotFound {

        Optional<StudentsEntity>  studentsEntity = studentsRepository.findById(id);

        if(studentsEntity.isEmpty()){
            throw new StudentIDNotFound(String.format("Student ID: %s Not Found", id));
        }
        return studentsEntity.get();
    }

    @Override
    public List<StudentsEntity> fetchStudentsByName(String name) throws StudentNameNotFound {
        logger.info("Fetching details for name: " + name);
        List<StudentsEntity> studentsEntities = studentsRepository.fetchStudentsByName(name);
        if(studentsEntities.isEmpty()){
            logger.info("No records found for the name: " + name);
            throw new StudentNameNotFound(String.format("Student Name: %s Not Found", name));
        }
        return studentsEntities;
    }

    @Override
    public void deleteStudentById(Long id) throws StudentIDNotFound {
        if(studentsRepository.findById(id).isEmpty()){
            logger.info("No records found for the ID: " + id);
            throw new StudentIDNotFound(String.format("Student ID: %s Not Found", id));
        }
        studentsRepository.deleteById(id);
    }

    @Override
    public StudentsEntity updateStudentById(Long id, StudentsEntity studentsEntityRequest) throws StudentIDNotFound {
        Optional<StudentsEntity> studentsEntity =  studentsRepository.findById(id);
        if(studentsEntity.isEmpty()){
            logger.info("No records found for the ID: " + id);
            throw new StudentIDNotFound("Student ID: " + id + " Not Found");
        }
        StudentsEntity studentsEntity1 = studentsEntity.get();
        if(Objects.nonNull(studentsEntity1.getName()) &&
        !"".equalsIgnoreCase(String.valueOf(studentsEntityRequest.getName()))){
            studentsEntity1.setName(studentsEntityRequest.getName());
        }

        if(Objects.nonNull(studentsEntity1.getDepartment()) &&
                !"".equalsIgnoreCase(String.valueOf(studentsEntityRequest.getDepartment()))){
            studentsEntity1.setDepartment(studentsEntityRequest.getDepartment());
        }

        if(Objects.nonNull(studentsEntity1.getState()) &&
                !"".equalsIgnoreCase(String.valueOf(studentsEntityRequest.getState()))){
            studentsEntity1.setState(studentsEntityRequest.getState());
        }
        return studentsRepository.save(studentsEntity1);
    }

}

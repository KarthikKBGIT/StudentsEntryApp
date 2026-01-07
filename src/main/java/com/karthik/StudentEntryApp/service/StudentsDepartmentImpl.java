package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.StudentIDNotFound;
import com.karthik.StudentEntryApp.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsDepartmentImpl implements StudentsService{
    @Autowired
    private StudentsRepository studentsRepository;

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
            throw new StudentIDNotFound("Student ID Not Found");
        }
        return studentsEntity.get();
    }

    @Override
    public List<StudentsEntity> fetchStudentsByName(String name) {
        return studentsRepository.fetchStudentsByName(name);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public StudentsEntity updateStudentById(Long id, StudentsEntity studentsEntity) {
        StudentsEntity studentsEntity1 = studentsRepository.findById(id).get();
        if(Objects.nonNull(studentsEntity1.getName()) &&
        !"".equalsIgnoreCase(String.valueOf(studentsEntity.getName()))){
            studentsEntity1.setName(studentsEntity.getName());
        }

        if(Objects.nonNull(studentsEntity1.getDepartment()) &&
                !"".equalsIgnoreCase(String.valueOf(studentsEntity.getDepartment()))){
            studentsEntity1.setDepartment(studentsEntity.getDepartment());
        }

        if(Objects.nonNull(studentsEntity1.getState()) &&
                !"".equalsIgnoreCase(String.valueOf(studentsEntity.getState()))){
            studentsEntity1.setState(studentsEntity.getState());
        }
        return studentsRepository.save(studentsEntity1);
    }

}

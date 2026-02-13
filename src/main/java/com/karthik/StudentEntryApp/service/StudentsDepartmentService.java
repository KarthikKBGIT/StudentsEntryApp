package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.StudentsEntity;
import com.karthik.StudentEntryApp.error.*;
import com.karthik.StudentEntryApp.repository.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class StudentsDepartmentService implements StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public StudentsEntity saveStudent(StudentsEntity studentsEntity) {
        studentsEntity.setCreated_at(Date.from(Instant.now()));
        return studentsRepository.save(studentsEntity);
    }

    @Override
    public List<StudentsEntity> fetchAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public Page<StudentsEntity> fetchStudentsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentsRepository.findAll(pageable);
    }

    @Override
    public StudentsEntity fetchStudentById(Long id) throws StudentNotFound {

        Optional<StudentsEntity> studentsEntity = studentsRepository.findById(id);

        if (studentsEntity.isEmpty()) {
            throw new StudentNotFound(String.format("Student with ID: %s Not Found", id));
        }
        return studentsEntity.get();
    }

    @Override
    public List<StudentsEntity> fetchStudentsByFirstName(String firstName) throws StudentNameNotFound, StudentNotFound {
        log.info("Fetching details for student's first name: " + firstName);
        List<StudentsEntity> studentsEntities = studentsRepository.fetchStudentsByFirstName(firstName);
        if (studentsEntities.isEmpty()) {
            log.info("No records found for the name: " + firstName);
            if (firstName == null || firstName.isEmpty()) {
                throw new StudentNameNotFound("Student Name on header is empty");
            }
            throw new StudentNotFound(String.format("Student with First Name: %s Not Found", firstName));
        }
        return studentsEntities;
    }

    @Override
    public List<StudentsEntity> fetchStudentsByLastName(String last_name) throws StudentNameNotFound, StudentNotFound {
        List<StudentsEntity> studentsEntities = studentsRepository.fetchStudentsByLastName(last_name);
        if(studentsEntities.isEmpty()){
            if(last_name==null || last_name.isEmpty()){
                throw new StudentNameNotFound("Student Name on header is empty");
            }
            throw new StudentNotFound("Student with Last Name: " + last_name + " Not Found");
        }
        return studentsEntities;
    }

    @Override
    public List<StudentsEntity> fetchByEmailId(String email) throws StudentEmailNotFound, StudentNotFound {
        List<StudentsEntity> studentsEntity = studentsRepository.fetchStudentsByEmail(email);
        if(studentsEntity.isEmpty()){
            if(email==null || email.isEmpty()){
                throw new StudentEmailNotFound("Student Email on header is empty");
            }
            throw new StudentNotFound(String.format("Student with Email: %s Not Found in Database", email));
        }
        return studentsEntity;
    }

    @Override
    public List<StudentsEntity> fetchByGender(String gender) throws InvalidGender, StudentNotFound {
        List<StudentsEntity> studentsEntities = studentsRepository.fetchStudentsByGender(gender);
        if(studentsEntities.isEmpty()){
            if(gender==null || gender.isEmpty()){
                throw new InvalidGender("Gender on header is empty");
            }
            throw new StudentNotFound("Student with Gender: " + gender + " Not Found in Database");
        }
        return studentsEntities;
    }

    @Override
    public void deleteStudentById(Long id) throws StudentIDNotFound {
        if (studentsRepository.findById(id).isEmpty()) {
            log.info("No records found for the ID: " + id);
            throw new StudentIDNotFound(String.format("Student ID: %s Not Found", id));
        }
        studentsRepository.deleteById(id);
    }

    @Override
    public StudentsEntity updateStudentById(Long id, StudentsEntity studentsEntityRequest) throws StudentNotFound, StudentIDNotFound, StudentNameNotFound {
        Optional<StudentsEntity> studentsEntity = studentsRepository.findById(id);
        if(studentsEntity.isEmpty()){
            if(id==null){
                throw new StudentIDNotFound("Student ID on header is empty");
            }
            throw new StudentNotFound("Student with ID: " + id + " Not Found");
        }
        StudentsEntity studentEntityToUpdate = studentsEntity.get();
        if(!studentsEntityRequest.getFirstname().isEmpty() && !Objects.equals(studentEntityToUpdate.getFirstname(), studentsEntityRequest.getFirstname())){
            studentEntityToUpdate.setLastname(studentsEntityRequest.getFirstname());
        }
        if(!studentsEntityRequest.getLastname().isEmpty() && !Objects.equals(studentEntityToUpdate.getLastname(), studentsEntityRequest.getLastname())){
            studentEntityToUpdate.setLastname(studentsEntityRequest.getLastname());
        }
        if(!studentsEntityRequest.getGender().isEmpty() && !Objects.equals(studentEntityToUpdate.getGender(), studentsEntityRequest.getGender())){
            studentEntityToUpdate.setGender(studentsEntityRequest.getGender());
        }
        if(!studentsEntityRequest.getEmail().isEmpty() && !Objects.equals(studentEntityToUpdate.getEmail(), studentsEntityRequest.getEmail())){
            studentEntityToUpdate.setEmail(studentsEntityRequest.getEmail());
        }
        if(!studentsEntityRequest.getDob().toString().isEmpty() && !Objects.equals(studentEntityToUpdate.getDob(), studentsEntityRequest.getDob())){
            studentEntityToUpdate.setDob(studentsEntityRequest.getDob());
        }
        if(!studentsEntityRequest.getPhoneNumber().toString().isEmpty() && !Objects.equals(studentEntityToUpdate.getPhoneNumber(), studentsEntityRequest.getPhoneNumber())){
            studentEntityToUpdate.setPhoneNumber(studentsEntityRequest.getPhoneNumber());
        }
        if(!studentsEntityRequest.getAddress().isEmpty() && !Objects.equals(studentEntityToUpdate.getAddress(), studentsEntityRequest.getAddress())){
            studentEntityToUpdate.setAddress(studentsEntityRequest.getAddress());
        }
        if(!studentsEntityRequest.getEnrollmentDate().toString().isEmpty() && !Objects.equals(studentEntityToUpdate.getEnrollmentDate(), studentsEntityRequest.getEnrollmentDate())){
            studentEntityToUpdate.setEnrollmentDate(studentsEntityRequest.getEnrollmentDate());
        }
        studentEntityToUpdate.setUpdated_at(Date.from(Instant.now()));
        return studentsRepository.save(studentEntityToUpdate);
    }

}

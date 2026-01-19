package com.karthik.StudentEntryApp.controller;


import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.error.EmailAlreadyExists;
import com.karthik.StudentEntryApp.error.UsernameAlreadyExists;
import com.karthik.StudentEntryApp.service.UsersServiceImplementation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UsersController {

    @Autowired
    private UsersServiceImplementation usersService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UsersEntity registerUser(@Valid @RequestBody UsersEntity usersRequest) throws EmailAlreadyExists, UsernameAlreadyExists {
        log.info("Received request to register user: " + usersRequest.getUsername());
        return usersService.saveUser(usersRequest);
    }

}

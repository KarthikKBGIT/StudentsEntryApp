package com.karthik.StudentEntryApp.controller;


import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.error.*;
import com.karthik.StudentEntryApp.service.UsersServiceImplementation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersServiceImplementation usersService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UsersEntity registerUser(@Valid @RequestBody UsersEntity usersRequest) throws EmailAlreadyExists, UsernameAlreadyExists, InvalidRole {
        log.info("Received request to register user: " + usersRequest.getUsername());
        return usersService.saveUser(usersRequest);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UsersEntity getUserById(@PathVariable Long id) throws UserNotFound {
        log.info("Received request to get user with id: " + id);
        return usersService.getUserById(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public UsersEntity updateUserById(@PathVariable Long id, @RequestBody UsersEntity userRequest) throws UserNotFound, UserIDNotFound, UsernameAlreadyExists {
        //I haven't added @Valid here because the user may want to update only one field and not all the fields, so I will handle the validation in the service layer.
        log.info("Received request to update user with id: " + id);
        return usersService.updateUserById(id, userRequest);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Long id) throws UserNotFound, UserIDNotFound {
        log.info("Received request to delete user with id: " + id);
        usersService.deleteUserById(id);
    }
}

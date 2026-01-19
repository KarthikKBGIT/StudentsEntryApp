package com.karthik.StudentEntryApp.controller;


import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.service.UsersServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UsersController {

    @Autowired
    private UsersServiceImplementation usersService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UsersEntity registerUser(@RequestBody UsersEntity usersRequest){
        log.info("Received request to register user: " + usersRequest.getUsername());
        return usersService.saveUser(usersRequest);
    }

}

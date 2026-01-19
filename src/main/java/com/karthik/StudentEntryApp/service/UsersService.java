package com.karthik.StudentEntryApp.service;


import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.error.EmailAlreadyExists;
import com.karthik.StudentEntryApp.error.UsernameAlreadyExists;

public interface UsersService {

    public UsersEntity saveUser(UsersEntity usersEntity) throws UsernameAlreadyExists, EmailAlreadyExists;
}

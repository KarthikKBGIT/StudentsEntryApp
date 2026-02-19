package com.karthik.StudentEntryApp.service;


import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.error.*;

public interface UsersService {

    public UsersEntity saveUser(UsersEntity usersEntity) throws UsernameAlreadyExists, EmailAlreadyExists, InvalidRole;

    public UsersEntity getUserById(Long id) throws UserNotFound;

    public UsersEntity updateUserById(Long id, UsersEntity usersEntity) throws UserNotFound, UserIDNotFound, UsernameAlreadyExists;

    public void deleteUserById(Long id) throws UserNotFound, UserIDNotFound;
}

package com.karthik.StudentEntryApp.service;


import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.error.*;

import java.util.List;
import java.util.Map;

public interface UsersService {

    public UsersEntity saveUser(UsersEntity usersEntity) throws UsernameAlreadyExists, EmailAlreadyExists, InvalidRole;

    public List<UsersEntity> getAllUsers();

    public UsersEntity getUserById(Long id) throws UserNotFound;

    public UsersEntity updateUserById(Long id, UsersEntity usersEntity) throws UserNotFound, UserIDNotFound, UsernameAlreadyExists, InvalidRole;

    public void deleteUserById(Long id) throws UserNotFound, UserIDNotFound;

    public Map<String, String> verifyUser(UsersEntity usersEntity);
}

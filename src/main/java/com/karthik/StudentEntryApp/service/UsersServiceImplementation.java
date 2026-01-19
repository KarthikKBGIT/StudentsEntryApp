package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersServiceImplementation implements UsersService {


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersEntity saveUser(UsersEntity usersEntity) {
        log.info("Saving user: " + usersEntity.getUsername());
        return usersRepository.save(usersEntity);
    }
}

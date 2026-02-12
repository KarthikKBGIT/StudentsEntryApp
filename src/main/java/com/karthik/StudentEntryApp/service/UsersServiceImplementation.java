package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.error.EmailAlreadyExists;
import com.karthik.StudentEntryApp.error.UsernameAlreadyExists;
import com.karthik.StudentEntryApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImplementation implements UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersEntity saveUser(UsersEntity usersEntity) throws UsernameAlreadyExists, EmailAlreadyExists {
        log.info("Saving user: " + usersEntity.getUsername());
        Optional<UsersEntity> usersResultwithSameUsername = usersRepository.findByUsername(usersEntity.getUsername());
        Optional<UsersEntity> usersResultwithSameEmail = usersRepository.findByEmail(usersEntity.getEmail());
        if(!usersResultwithSameUsername.isEmpty()){
            throw new UsernameAlreadyExists("Username already exists: " + usersEntity.getUsername());
        }
        if(!usersResultwithSameEmail.isEmpty()) {
            throw new EmailAlreadyExists("Email already exists: " + usersEntity.getEmail());
        }
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        return usersRepository.save(usersEntity);
    }
}

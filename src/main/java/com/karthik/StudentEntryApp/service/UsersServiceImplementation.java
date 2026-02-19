package com.karthik.StudentEntryApp.service;

import com.karthik.StudentEntryApp.entity.UsersEntity;
import com.karthik.StudentEntryApp.error.*;
import com.karthik.StudentEntryApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImplementation implements UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public UsersEntity saveUser(UsersEntity usersEntity) throws UsernameAlreadyExists, EmailAlreadyExists, InvalidRole {
        log.info("Saving user: " + usersEntity.getUsername());
        Optional<UsersEntity> usersResultwithSameUsername = usersRepository.findByUsername(usersEntity.getUsername());
        Optional<UsersEntity> usersResultwithSameEmail = usersRepository.findByEmail(usersEntity.getEmail());
        if (!usersResultwithSameUsername.isEmpty()) {
            throw new UsernameAlreadyExists("Username already exists: " + usersEntity.getUsername());
        }
        if (!usersResultwithSameEmail.isEmpty()) {
            throw new EmailAlreadyExists("Email already exists: " + usersEntity.getEmail());
        }
        if(usersEntity.getRole().equalsIgnoreCase("ADMIN") || usersEntity.getRole().equalsIgnoreCase("USER")){
            usersEntity.setRole(usersEntity.getRole().toUpperCase());
        }
        else{
            log.error("Invalid role: " + usersEntity.getRole() + ". Role must be either ADMIN or USER");
            throw new InvalidRole("Invalid role: " + usersEntity.getRole() + ". Role must be either ADMIN or USER");
        }
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        usersEntity.setCreated_at(Date.from(Instant.now()));
        return usersRepository.save(usersEntity);
    }

    @Override
    public UsersEntity getUserById(Long id) throws UserNotFound {
        log.info("Retrieving user with id: " + id);
        Optional<UsersEntity> userWithId = usersRepository.findById(id);
        if(userWithId.isEmpty()){
            throw new UserNotFound("User with id " + id + " not found");
        }
        return userWithId.get();
    }

    @Override
    public UsersEntity updateUserById(Long id, UsersEntity usersEntity) throws UserNotFound, UserIDNotFound, UsernameAlreadyExists, InvalidRole {
        if(id == null){
            log.info("User ID in the request path cannot be null");
            throw new UserIDNotFound("User ID in the request path cannot be null");
        }
        log.info("Updating user with id: " + id);
        log.info("Retrieving user with id: " + id);
        Optional<UsersEntity> userEntityToBeUpdated = usersRepository.findById(id);
        if(userEntityToBeUpdated.isEmpty()){
            throw new UserNotFound("User with id " + id + " not found");
        }
        UsersEntity userToBeUpdated = userEntityToBeUpdated.get();
        if(usersEntity.getUsername() != null){
            log.error("Username cannot be updated");
            throw new UsernameAlreadyExists("Username cannot be updated");
        }
        if(usersEntity.getPassword() != null && !usersEntity.getPassword().equals(userToBeUpdated.getPassword())){
            userToBeUpdated.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        }
        if(usersEntity.getEmail() != null && !usersEntity.getEmail().equals(userToBeUpdated.getEmail())){
            userToBeUpdated.setEmail(usersEntity.getEmail());
        }
        if(usersEntity.getRole() != null && !usersEntity.getRole().equals(userToBeUpdated.getRole())){
            if(usersEntity.getRole().equalsIgnoreCase("ADMIN") || usersEntity.getRole().equalsIgnoreCase("USER")){
                userToBeUpdated.setRole(usersEntity.getRole().toUpperCase());
            }
            else{
                log.error("Invalid role: " + usersEntity.getRole() + ". Role must be either ADMIN or USER");
                throw new InvalidRole("Invalid role: " + usersEntity.getRole() + ". Role must be either ADMIN or USER");
            }
            userToBeUpdated.setRole(usersEntity.getRole());
        }
        userToBeUpdated.setUpdated_at(Date.from(Instant.now()));
        return usersRepository.save(userToBeUpdated);
    }

    @Override
    public void deleteUserById(Long id) throws UserNotFound, UserIDNotFound {
        log.info("Deleting user with id: " + id);
        if(id == null){
            log.info("User ID in the request path cannot be null");
            throw new UserIDNotFound("User ID in the request path cannot be null");
        }
        Optional<UsersEntity> userWithId = usersRepository.findById(id);
        if(userWithId.isEmpty()) {
            throw new UserNotFound("User with id " + id + " not found");
        }
        usersRepository.deleteById(id);
    }

    @Override
    public Map<String, String> verifyUser(UsersEntity usersEntity) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usersEntity.getUsername(),
                        usersEntity.getPassword()));
        Map<String,String> map = new HashMap<>();
        if(authentication.isAuthenticated()){
            map.put("token", jwtService.generateToken(usersEntity.getUsername()));
        }
        return map;
    }
}

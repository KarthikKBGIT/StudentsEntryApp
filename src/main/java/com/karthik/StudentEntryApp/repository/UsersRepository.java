package com.karthik.StudentEntryApp.repository;

import com.karthik.StudentEntryApp.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    @Query(value = "select * from USERS_ENTITY where username = :username", nativeQuery = true)
    public Optional<UsersEntity> findByUsername(@Param("username") String username);

    @Query(value = "select * from USERS_ENTITY where email =  :email", nativeQuery = true)
    public Optional<UsersEntity> findByEmail(@Param("email") String email);
}

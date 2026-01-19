package com.karthik.StudentEntryApp.repository;

import com.karthik.StudentEntryApp.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    @Query(value = "select * from USERS_ENTITY where username = :username", nativeQuery = true)
    public List<UsersEntity> findByUsername(@Param("username") String username);

    @Query(value = "select * from USERS_ENTITY where email =  :email", nativeQuery = true)
    public List<UsersEntity> findByEmail(@Param("email") String email);
}

package com.karthik.StudentEntryApp.repository;

import com.karthik.StudentEntryApp.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    @Query(value = "select * from USERS_ENTITY where username = :username", nativeQuery = true)
    public Optional<UsersEntity> findByUsername(@Param("username") String username);

    @Query(value = "select * from USERS_ENTITY where email =  :email", nativeQuery = true)
    public Optional<UsersEntity> findByEmail(@Param("email") String email);

    @Query(value = "select * from users_entity where id = :id", nativeQuery = true )
    public Optional<UsersEntity> findById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from users_entity where id = :id", nativeQuery = true)
    public void deleteById(Long id);
}

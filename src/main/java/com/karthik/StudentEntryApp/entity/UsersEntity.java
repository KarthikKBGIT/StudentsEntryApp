package com.karthik.StudentEntryApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Role is mandatory")
    private String role;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created_at;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updated_at;

}

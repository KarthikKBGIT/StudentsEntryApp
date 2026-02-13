package com.karthik.StudentEntryApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotBlank(message = "Last name is mandatory")
    private String lastname;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;
    @NotNull(message = "Date of Birth is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date dob;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @NotNull(message = "Phone number is mandatory")
    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be at most 10 digits")
    private Long phoneNumber;
    @NotEmpty(message = "Address is mandatory")
    private String address;
    @NotNull(message = "Enrollment date is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date enrollmentDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created_at;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updated_at;
}

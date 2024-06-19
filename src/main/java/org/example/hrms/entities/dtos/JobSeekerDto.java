package org.example.hrms.entities.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class JobSeekerDto {
    private int id;
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "National identity number is mandatory")
    @Size(min = 11, max = 11, message = "National identity number must be 11 characters long")
    private String nationalIdentityNumber;

    @NotNull(message = "Birth year is mandatory")
    private int birthYear;

    @NotBlank(message = "Email address is mandatory")
    @Email(message = "Invalid email address")
    private String eMailAddress;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}

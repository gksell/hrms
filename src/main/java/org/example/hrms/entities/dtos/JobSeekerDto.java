package org.example.hrms.entities.dtos;

import lombok.Data;

@Data
public class JobSeekerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String nationalIdentityNumber;
    private int birthYear;
    private String eMailAddress;
    private String password;
}

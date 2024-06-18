package org.example.hrms.entities.dtos;

import lombok.Data;

@Data
public class EmployerDto {
    private int id;
    private String companyName;
    private String website;
    private String eMail;
    private String phoneNumber;
    private String password;
}

package org.example.hrms.entities.dtos.CV;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GraduateDto {
    private String department;
    private String startDate;
    private LocalDate graduateDate;
    private boolean graduated;
    private String schoolName;
}

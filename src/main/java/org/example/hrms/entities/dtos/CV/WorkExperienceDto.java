package org.example.hrms.entities.dtos.CV;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkExperienceDto {
    private String companyName;
    private String jobPosition;
    private LocalDate startYear;
    private LocalDate endYear;
    private boolean isEnd;
}

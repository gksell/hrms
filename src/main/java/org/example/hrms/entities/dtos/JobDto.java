package org.example.hrms.entities.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class JobDto {
    private int id;
    private String companyName;
    private String jobPositionName;
    private int openPositions;
    private int positionId;
    private int cityId;
    private BigDecimal maxSalary;
    private BigDecimal minSalary;
    private LocalDate created_date;
    private LocalDate applicationDeadLine;
    private String jobDescription;
    private int employerId;
    private Boolean isActive;
}

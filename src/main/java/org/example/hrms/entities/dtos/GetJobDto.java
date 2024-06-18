package org.example.hrms.entities.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GetJobDto {
    private int id;
    private String companyName;
    private String jobPositionName;
    private int openPositions;
    private BigDecimal maxSalary;
    private BigDecimal minSalary;
    private LocalDate created_date;
    private LocalDate applicationDeadLine;
    private String jobDescription;
}

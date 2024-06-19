package org.example.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPositionDto {
    private int id;
    @Size(min = 3, message = "Position name must be at least 3 characters long")
    private String positionName;
}

package org.example.hrms.entities.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class JobStatusChangeDto {

    @Min(0)
    private int id;

    @NotNull
    private boolean status;
}

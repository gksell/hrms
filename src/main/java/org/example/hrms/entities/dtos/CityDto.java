package org.example.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityDto {
    private int id;
    private String cityName;
    private String cityCode;
}

package org.example.hrms.business.utils.mappers;

import org.example.hrms.entities.concretes.City;
import org.example.hrms.entities.dtos.CityDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CityMapper {

    public static List<CityDto> toCityDto(List<City> cities) {
        if (cities == null) {
            return Collections.emptyList();
        }

        return cities.stream()
                .map(city -> new CityDto(
                        city.getId(),
                        city.getCityName(),
                        city.getCityCode()
                ))
                .collect(Collectors.toList());
    }
}

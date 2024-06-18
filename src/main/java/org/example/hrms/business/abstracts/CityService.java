package org.example.hrms.business.abstracts;

import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.entities.concretes.City;
import org.example.hrms.entities.dtos.CityDto;

import java.util.List;

public interface CityService {
    DataResult<List<CityDto>> getAllCity();
}

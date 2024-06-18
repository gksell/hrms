package org.example.hrms.business.concretes;

import org.example.hrms.business.abstracts.CityService;
import org.example.hrms.business.utils.mappers.CityMapper;
import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.core.utilities.results.SuccessDataResult;
import org.example.hrms.dataAccess.abstracts.CityDao;
import org.example.hrms.entities.concretes.City;
import org.example.hrms.entities.dtos.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {
    private final CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<CityDto>> getAllCity() {
        return new SuccessDataResult<>(CityMapper.toCityDto(this.cityDao.findAll()),"Şehirler listelendi.") ;
    }
}

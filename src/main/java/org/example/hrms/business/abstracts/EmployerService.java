package org.example.hrms.business.abstracts;


import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.concretes.Employer;
import org.example.hrms.entities.dtos.AllEmployerDto;
import org.example.hrms.entities.dtos.EmployerDto;

import java.util.List;

public interface EmployerService {
    DataResult<List<AllEmployerDto>> getEmployer();
    Result add(EmployerDto employerDto);
}

package org.example.hrms.business.abstracts;

import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.dtos.JobPositionDto;

import java.util.List;

public interface JobPositionService {
    DataResult<List<JobPositionDto>> getJobPositions();
    Result add(JobPositionDto jobPositionDto);
}

package org.example.hrms.business.abstracts;

import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.dtos.GetJobDto;
import org.example.hrms.entities.dtos.JobDto;
import org.example.hrms.entities.dtos.JobStatusChangeDto;

import java.util.List;

public interface JobService {
    Result add(JobDto jobDto);
    DataResult<List<GetJobDto>> getAllActiveJob();
    DataResult<List<GetJobDto>> getAllActiveJobDesc();
    Result toggleJobStatus(JobStatusChangeDto jobStatusChangeDto);
    DataResult<List<GetJobDto>> getJobByCompanyId(int companyId);
}

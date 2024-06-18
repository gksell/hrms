package org.example.hrms.business.abstracts;

import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.concretes.JobSeeker;
import org.example.hrms.entities.dtos.JobSeekerDto;

public interface JobSeekerService {
    Result add(JobSeekerDto jobSeekerDto);
}

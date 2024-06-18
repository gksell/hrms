package org.example.hrms.business.utils.mappers;

import org.example.hrms.entities.concretes.JobSeeker;
import org.example.hrms.entities.dtos.JobSeekerDto;
import org.springframework.stereotype.Component;

@Component
public class JobSeekerMapper {

    public static JobSeeker mapJobSeeker(JobSeekerDto jobSeekerDto) {
        if (jobSeekerDto == null){
            return null;
        }
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setId(jobSeekerDto.getId());
        jobSeeker.setBirthYear(jobSeekerDto.getBirthYear());
        jobSeeker.setNationalIdentityNumber(jobSeekerDto.getNationalIdentityNumber());
        jobSeeker.setPassword(jobSeekerDto.getPassword());
        jobSeeker.setFirstName(jobSeekerDto.getFirstName());
        jobSeeker.setLastName(jobSeekerDto.getLastName());
        jobSeeker.setEMailAddress(jobSeekerDto.getEMailAddress());
        return jobSeeker;
    }
}

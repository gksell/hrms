package org.example.hrms.business.utils.mappers;

import org.example.hrms.dataAccess.abstracts.CityDao;
import org.example.hrms.dataAccess.abstracts.EmployerDao;
import org.example.hrms.dataAccess.abstracts.JobPositionDao;
import org.example.hrms.entities.concretes.City;
import org.example.hrms.entities.concretes.Employer;
import org.example.hrms.entities.concretes.Job;
import org.example.hrms.entities.concretes.JobPosition;
import org.example.hrms.entities.dtos.GetJobDto;
import org.example.hrms.entities.dtos.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobMapper {

    private static CityDao cityDao;
    private static JobPositionDao jobPositionDao;
    private static EmployerDao employerDao;

    @Autowired
    public void init(CityDao cityDao, JobPositionDao jobPositionDao,EmployerDao employerDao) {
        JobMapper.cityDao = cityDao;
        JobMapper.jobPositionDao = jobPositionDao;
        JobMapper.employerDao = employerDao;
    }

    public static JobDto toJobDto(Job job) {
        if (job == null) {
            return null;
        }
        JobDto dto = new JobDto();
        dto.setJobDescription(job.getJobDescription());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        dto.setOpenPositions(job.getOpenPositions());
        dto.setApplicationDeadLine(job.getApplicationDeadLine());
        dto.setIsActive(job.getIsActive());
        if (job.getCity() != null) {
            dto.setCityId(job.getCity().getId());
        }
        if (job.getJobPosition() != null) {
            dto.setPositionId(job.getJobPosition().getId());
        }
        return dto;
    }

    public static Job toJobEntity(JobDto jobDto) {
        Job job = new Job();
        job.setJobDescription(jobDto.getJobDescription());
        job.setMinSalary(jobDto.getMinSalary());
        job.setMaxSalary(jobDto.getMaxSalary());
        job.setOpenPositions(jobDto.getOpenPositions());
        job.setApplicationDeadLine(jobDto.getApplicationDeadLine());
        job.setIsActive(jobDto.getIsActive());

        if (jobDto.getCityId() != 0) {
            City city = cityDao.getById(jobDto.getCityId());
            job.setCity(city);
        }

        if (jobDto.getPositionId() != 0) {
            JobPosition jobPosition = jobPositionDao.getById(jobDto.getPositionId());
            job.setJobPosition(jobPosition);
        }

        if (jobDto.getEmployerId() != 0){
            Employer employer = employerDao.getById(jobDto.getEmployerId());
            job.setEmployer(employer);
        }

        return job;
    }

    public static List<GetJobDto> toJobListOutputDto(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return List.of();
        }

        return jobs.stream()
                .map(JobMapper::toJobListOutputDto)
                .collect(Collectors.toList());
    }

    private static GetJobDto toJobListOutputDto(Job job) {
        if (job == null) {
            return null;
        }

        GetJobDto jobListOutputDto = new GetJobDto();
        jobListOutputDto.setId(job.getId());
        jobListOutputDto.setCompanyName(job.getEmployer().getCompanyName());
        jobListOutputDto.setJobPositionName(job.getJobPosition().getPositionName());
        jobListOutputDto.setOpenPositions(job.getOpenPositions());
        jobListOutputDto.setCreated_date(job.getCreatedDate());
        jobListOutputDto.setJobDescription(job.getJobDescription());
        jobListOutputDto.setApplicationDeadLine(job.getApplicationDeadLine());

        return jobListOutputDto;
    }
}

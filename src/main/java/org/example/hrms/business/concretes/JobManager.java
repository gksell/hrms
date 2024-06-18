package org.example.hrms.business.concretes;

import org.example.hrms.business.abstracts.JobService;
import org.example.hrms.business.utils.mappers.JobMapper;
import org.example.hrms.core.helper.ValidationHelper;
import org.example.hrms.core.utilities.results.*;
import org.example.hrms.dataAccess.abstracts.JobDao;
import org.example.hrms.entities.concretes.Job;
import org.example.hrms.entities.dtos.GetJobDto;
import org.example.hrms.entities.dtos.JobDto;
import org.example.hrms.entities.dtos.JobStatusChangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobManager implements JobService {
    private final JobDao jobDao;

    @Autowired
    public JobManager(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public Result add(JobDto jobDto) {
        if (jobDto.getPositionId() == 0
                || jobDto.getCityId() == 0
                || ValidationHelper.isNullOrWhitespace(jobDto.getJobDescription())
                || jobDto.getOpenPositions() == 0) {
            return new ErrorResult("Şehir,İş Pozisyonu,İş Tanımı,Açık Pozisyon sayısı zorunludur");
        }
        this.jobDao.save(JobMapper.toJobEntity(jobDto));
        return new SuccessResult("İlan başarıyla açılmıştır.");
    }

    @Override
    public DataResult<List<GetJobDto>> getAllActiveJob() {
        List<Job> jobs = this.jobDao.findByIsActiveTrue();
        return new SuccessDataResult<>(JobMapper.toJobListOutputDto(jobs));
    }

    @Override
    public DataResult<List<GetJobDto>> getAllActiveJobDesc() {
        List<Job> jobs = this.jobDao.findByIsActiveTrueOrderByCreatedDateDesc();
        return new SuccessDataResult<>(JobMapper.toJobListOutputDto(jobs));
    }

    @Override
    public Result toggleJobStatus(JobStatusChangeDto jobStatusChangeDto) {
        Job job = jobDao.findById(jobStatusChangeDto.getId())
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobStatusChangeDto.getId()));
        job.setIsActive(jobStatusChangeDto.isStatus());
        jobDao.save(job);
        return new SuccessResult("Başarılı");
    }

    @Override
    public DataResult<List<GetJobDto>> getJobByCompanyId(int companyId) {
        List<Job> jobs = this.jobDao.findByEmployerIdAndIsActiveTrue(companyId);
        return new SuccessDataResult<>(JobMapper.toJobListOutputDto(jobs));
    }
}

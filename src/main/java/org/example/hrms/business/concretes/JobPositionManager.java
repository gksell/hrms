package org.example.hrms.business.concretes;

import org.example.hrms.business.abstracts.JobPositionService;
import org.example.hrms.business.utils.mappers.JobPositionMapper;
import org.example.hrms.core.helper.ValidationHelper;
import org.example.hrms.core.utilities.results.*;
import org.example.hrms.dataAccess.abstracts.JobPositionDao;
import org.example.hrms.entities.concretes.JobPosition;
import org.example.hrms.entities.dtos.JobPositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private final JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public DataResult<List<JobPositionDto>> getJobPositions() {
        return new SuccessDataResult<>(JobPositionMapper.mapToJobPositionDtoList(this.jobPositionDao.findAll()));
    }

    @Override
    public Result add(JobPositionDto jobPositionDto) {

        if (jobPositionDto == null || ValidationHelper.isNullOrWhitespace(jobPositionDto.getPositionName())) {
            return new ErrorResult("Pozisyon bilgisi dolu olmalıdır.");
        }

        JobPosition jp = this.jobPositionDao.findByPositionName(jobPositionDto.getPositionName());
        if (jp != null) {
            return new ErrorResult("Bu pozisyon zaten eklenmiş");
        }

        this.jobPositionDao.save(JobPositionMapper.mapToJobPosition(jobPositionDto));
        return new SuccessResult("Pozisyon kaydı başarılı.");
    }
}

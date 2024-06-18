package org.example.hrms.business.concretes;

import org.example.hrms.business.abstracts.JobSeekerService;
import org.example.hrms.business.utils.mappers.JobSeekerMapper;
import org.example.hrms.core.external.services.EmailService;
import org.example.hrms.core.external.services.MernisService;
import org.example.hrms.core.helper.ValidationHelper;
import org.example.hrms.core.utilities.results.ErrorResult;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.core.utilities.results.SuccessResult;
import org.example.hrms.dataAccess.abstracts.JobSeekersDao;
import org.example.hrms.entities.concretes.JobSeeker;
import org.example.hrms.entities.dtos.JobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekersDao jobSeekersDao;
    private final EmailService emailService;
    private final MernisService mernisService;

    @Autowired
    public JobSeekerManager(JobSeekersDao jobSeekersDao, EmailService emailService, MernisService mernisService) {
        this.jobSeekersDao = jobSeekersDao;
        this.emailService = emailService;
        this.mernisService = mernisService;
    }

    @Override
    public Result add(JobSeekerDto jobSeekerDto) {

        if (jobSeekerNullCheck(jobSeekerDto)) {
            return new ErrorResult("Tüm alanlar zorunludur.");
        }

        // Mernis doğrulaması

        if (!this.mernisService.isRealPerson(jobSeekerDto.getNationalIdentityNumber(),jobSeekerDto.getFirstName(),jobSeekerDto.getLastName(),jobSeekerDto.getBirthYear())){
            return new ErrorResult("Kişi doğrulanamamıştır. Bilgilerinizi eksiksiz giriniz.");
        }

        if (this.jobSeekersDao.existsJobSeekerByeMailAddressAndNationalIdentityNumber(jobSeekerDto.getEMailAddress(), jobSeekerDto.getNationalIdentityNumber())) {
            return new ErrorResult("Bu kayıt daha önceden gerçekleştirilmiştir.");
        }


        this.emailService.send(jobSeekerDto.getEMailAddress(), "İş arayan kayıt hk.", "Aktivasyon için yönlendirme.");
        this.jobSeekersDao.save(JobSeekerMapper.mapJobSeeker(jobSeekerDto));
        return new SuccessResult("Kayıt başarılı lütfen mail adresini kontrol ediniz.");
    }

    private boolean jobSeekerNullCheck(JobSeekerDto jobSeekerDto) {
        return ValidationHelper.isNullOrWhitespace(jobSeekerDto.getEMailAddress())
                || ValidationHelper.isNullOrWhitespace(jobSeekerDto.getFirstName())
                || ValidationHelper.isNullOrWhitespace(jobSeekerDto.getLastName())
                || ValidationHelper.isNullOrWhitespace(jobSeekerDto.getNationalIdentityNumber())
                || ValidationHelper.isNullOrWhitespace(jobSeekerDto.getPassword());
    }
}

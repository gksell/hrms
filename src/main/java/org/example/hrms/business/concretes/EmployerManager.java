package org.example.hrms.business.concretes;

import org.example.hrms.business.abstracts.EmployerService;
import org.example.hrms.business.utils.mappers.EmployerMapper;
import org.example.hrms.core.external.services.EmailService;
import org.example.hrms.core.helper.ValidationHelper;
import org.example.hrms.core.utilities.results.*;
import org.example.hrms.dataAccess.abstracts.EmployerDao;
import org.example.hrms.entities.concretes.Employer;
import org.example.hrms.entities.dtos.AllEmployerDto;
import org.example.hrms.entities.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final EmailService emailService;

    @Autowired
    public EmployerManager(EmployerDao employerDao, EmailService emailService) {
        this.employerDao = employerDao;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<AllEmployerDto>> getEmployer() {
        return new SuccessDataResult<>(EmployerMapper.mapToAllEmployerList(this.employerDao.findAll()),"İşverenler Listelendi");
    }

    @Override
    public Result add(EmployerDto employerDto) {
        Employer employerCheck = this.employerDao.findEmployerByeMail(employerDto.getEMail());
        if (employerNullCheck(employerDto)){
            return new ErrorResult("Alanlar boş geçilemez.");
        }

        if (employerCheck != null){
            return new ErrorResult("Daha önce bu kayıt yapılmış. Aktivasyon kodunuz gelmediyse bizimle iletişime geçin.");
        }

        if (!ValidationHelper.isValidWebsite(employerDto.getWebsite())){
            return new ErrorResult("Website geçerli bir formatta olmalı.");
        }
        if (!ValidationHelper.isValidEmail(employerDto.getEMail(),employerDto.getWebsite())){
            return new ErrorResult("Email geçerli bir formatta olmalı ve domain ile uyuşmalıdır.");
        }

        this.employerDao.save(EmployerMapper.mapToEmployer(employerDto));
        this.emailService.send(employerDto.getEMail(),"Yeni şirket kaydı hk.","Linke tıklayın.");

        return new SuccessResult("Kayıt başarılı. Lütfen maile gelen aktivasyon linkine tıklayınız.");
    }

    private boolean employerNullCheck(EmployerDto employerDto) {
        return ValidationHelper.isNullOrWhitespace(employerDto.getCompanyName())
                || ValidationHelper.isNullOrWhitespace(employerDto.getPassword())
                || ValidationHelper.isNullOrWhitespace(employerDto.getWebsite())
                || ValidationHelper.isNullOrWhitespace(employerDto.getEMail());

    }
}

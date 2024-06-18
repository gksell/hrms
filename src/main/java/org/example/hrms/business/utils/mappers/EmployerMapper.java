package org.example.hrms.business.utils.mappers;

import org.example.hrms.entities.concretes.Employer;
import org.example.hrms.entities.dtos.AllEmployerDto;
import org.example.hrms.entities.dtos.EmployerDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployerMapper {

    public static List<AllEmployerDto> mapToAllEmployerList(List<Employer> employers) {
        if (employers == null || employers.isEmpty()) {
            return Collections.emptyList();
        }
        List<AllEmployerDto> allEmployerDtos = new ArrayList<>();
        for (Employer employer : employers) {
            AllEmployerDto dto = new AllEmployerDto();
            dto.setCompanyName(employer.getCompanyName());
            dto.setWebsite(employer.getWebsite());
            dto.setEMail(employer.getEMail());
            dto.setPhoneNumber(employer.getPhone());
            allEmployerDtos.add(dto);
        }
        return allEmployerDtos;
    }

    public static Employer mapToEmployer(EmployerDto employerDto) {
        if (employerDto == null) {
            return null;
        }
        Employer employer = new Employer();
        employer.setId(employerDto.getId());
        employer.setCompanyName(employerDto.getCompanyName());
        employer.setWebsite(employerDto.getWebsite());
        employer.setEMail(employerDto.getEMail());
        employer.setPhone(employerDto.getPhoneNumber());
        employer.setPassword(employerDto.getPassword());
        return employer;
    }
}

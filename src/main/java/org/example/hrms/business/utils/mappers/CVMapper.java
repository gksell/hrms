package org.example.hrms.business.utils.mappers;

import org.example.hrms.dataAccess.abstracts.JobSeekersDao;
import org.example.hrms.entities.concretes.JobSeeker;
import org.example.hrms.entities.concretes.cv.*;
import org.example.hrms.entities.dtos.CV.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CVMapper {
    private static JobSeekersDao jobSeekersDao;

    @Autowired
    public void init(JobSeekersDao jobSeekersDao) {
        CVMapper.jobSeekersDao = jobSeekersDao;
    }

    public static CV mapToCV(CVDto cvDto) {
        if (cvDto == null) {
            return null;
        }

        CV cv = new CV();
        JobSeeker jobSeeker = jobSeekersDao.getById(cvDto.getJobSeekerId());
        cv.setJobSeeker(jobSeeker);
        cv.setCoverLetter(cvDto.getCoverLetter());
        cv.setGithubAddress(cvDto.getGithubAddress());
        cv.setLinkedinAddress(cvDto.getLinkedinAddress());
        cv.setPhoto(cvDto.getPhoto());

        // Diller listesini mapleyin ve cv referansını ayarlayın
        if (cvDto.getLanguages() != null) {
            List<Language> languages = cvDto.getLanguages().stream()
                    .map(languageDto -> {
                        Language language = new Language();
                        language.setLanguageName(languageDto.getLanguageName());
                        language.setLevel(languageDto.getLevel());
                        language.setCv(cv);  // CV referansını ayarla
                        return language;
                    })
                    .collect(Collectors.toList());
            cv.setLanguages(languages);
        }

        // Mezunlar listesini mapleyin ve cv referansını ayarlayın
        if (cvDto.getGraduates() != null) {
            List<Graduate> graduates = cvDto.getGraduates().stream()
                    .map(graduateDto -> {
                        Graduate graduate = new Graduate();
                        graduate.setSchoolName(graduateDto.getSchoolName());
                        graduate.setDepartment(graduateDto.getDepartment());
                        graduate.setGraduateDate(graduateDto.getGraduateDate());
                        graduate.setCv(cv);  // CV referansını ayarla
                        return graduate;
                    })
                    .collect(Collectors.toList());
            cv.setGraduates(graduates);
        }

        // Beceriler listesini mapleyin ve cv referansını ayarlayın
        if (cvDto.getSkills() != null) {
            List<Skill> skills = cvDto.getSkills().stream()
                    .map(skillDto -> {
                        Skill skill = new Skill();
                        skill.setSkillName(skillDto.getSkillName());
                        skill.setLevel(skillDto.getLevel());
                        skill.setCv(cv);  // CV referansını ayarla
                        return skill;
                    })
                    .collect(Collectors.toList());
            cv.setSkills(skills);
        }

        // İş deneyimleri listesini mapleyin ve cv referansını ayarlayın
        if (cvDto.getWorkExperiences() != null) {
            List<WorkExperience> workExperiences = cvDto.getWorkExperiences().stream()
                    .map(workExperienceDto -> {
                        WorkExperience workExperience = new WorkExperience();
                        workExperience.setCompanyName(workExperienceDto.getCompanyName());
                        workExperience.setJobPosition(workExperienceDto.getJobPosition());
                        workExperience.setStartYear(workExperienceDto.getStartYear());
                        workExperience.setEndYear(workExperienceDto.getEndYear());
                        workExperience.setCv(cv);
                        return workExperience;
                    })
                    .collect(Collectors.toList());
            cv.setWorkExperiences(workExperiences);
        }

        return cv;
    }

    public static List<CVDto> mapToCVList(List<CV> cvs) {
        return cvs.stream()
                .map(cv -> mapToCVDto(cv))
                .collect(Collectors.toList());
    }

    private static CVDto mapToCVDto(CV cv) {
        CVDto cvDto = new CVDto();

        cvDto.setJobSeekerId(cv.getJobSeeker().getId());
        cvDto.setCoverLetter(cv.getCoverLetter());
        cvDto.setGithubAddress(cv.getGithubAddress());
        cvDto.setLinkedinAddress(cv.getLinkedinAddress());
        cvDto.setPhoto(cv.getPhoto());

        // Language listesini LanguageDto listesine dönüştürme
        List<LanguageDto> languageDtos = cv.getLanguages().stream()
                .map(language -> {
                    LanguageDto languageDto = new LanguageDto();
                    languageDto.setLanguageName(language.getLanguageName());
                    languageDto.setLevel(language.getLevel());
                    return languageDto;
                })
                .collect(Collectors.toList());
        cvDto.setLanguages(languageDtos);

        // Graduate listesini GraduateDto listesine dönüştürme
        List<GraduateDto> graduateDtos = cv.getGraduates().stream()
                .map(graduate -> {
                    GraduateDto graduateDto = new GraduateDto();
                    graduateDto.setSchoolName(graduate.getSchoolName());
                    graduateDto.setDepartment(graduate.getDepartment());
                    graduateDto.setStartDate(String.valueOf(graduate.getStartDate()));
                    graduateDto.setGraduateDate(graduate.getGraduateDate());
                    return graduateDto;
                })
                .collect(Collectors.toList());
        cvDto.setGraduates(graduateDtos);

        // Skill listesini SkillDto listesine dönüştürme
        List<SkillDto> skillDtos = cv.getSkills().stream()
                .map(skill -> {
                    SkillDto skillDto = new SkillDto();
                    skillDto.setSkillName(skill.getSkillName());
                    skillDto.setLevel(skill.getLevel());
                    return skillDto;
                })
                .collect(Collectors.toList());
        cvDto.setSkills(skillDtos);

        // WorkExperience listesini WorkExperienceDto listesine dönüştürme
        List<WorkExperienceDto> workExperienceDtos = cv.getWorkExperiences().stream()
                .map(workExperience -> {
                    WorkExperienceDto workExperienceDto = new WorkExperienceDto();
                    workExperienceDto.setCompanyName(workExperience.getCompanyName());
                    workExperienceDto.setJobPosition(workExperience.getJobPosition());
                    workExperienceDto.setStartYear(workExperience.getStartYear());
                    workExperienceDto.setEndYear(workExperience.getEndYear());
                    return workExperienceDto;
                })
                .collect(Collectors.toList());
        cvDto.setWorkExperiences(workExperienceDtos);

        return cvDto;
    }
}

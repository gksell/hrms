package org.example.hrms.entities.dtos.CV;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CVDto {
    private int jobSeekerId;
    private String coverLetter;
    private String githubAddress;
    private String linkedinAddress;
    private String photo;
    private List<LanguageDto> languages;
    private List<GraduateDto> graduates;
    private List<SkillDto> skills;
    private List<WorkExperienceDto> workExperiences;
}

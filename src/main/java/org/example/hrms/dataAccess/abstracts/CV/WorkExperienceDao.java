package org.example.hrms.dataAccess.abstracts.CV;

import org.example.hrms.entities.concretes.cv.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkExperienceDao extends JpaRepository<WorkExperience, Integer> {
}

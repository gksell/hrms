package org.example.hrms.dataAccess.abstracts.CV;

import org.example.hrms.entities.concretes.cv.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDao extends JpaRepository<Skill, Integer> {
}

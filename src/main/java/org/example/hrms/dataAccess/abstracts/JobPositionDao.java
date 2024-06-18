package org.example.hrms.dataAccess.abstracts;

import org.example.hrms.entities.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {
    JobPosition findByPositionName(String positionName);
}

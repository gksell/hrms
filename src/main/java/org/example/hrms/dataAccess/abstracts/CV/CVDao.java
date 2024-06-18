package org.example.hrms.dataAccess.abstracts.CV;

import org.example.hrms.entities.concretes.cv.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CVDao extends JpaRepository<CV,Integer> {
    List<CV> findByJobSeeker_Id(int jobSeekerId);
}

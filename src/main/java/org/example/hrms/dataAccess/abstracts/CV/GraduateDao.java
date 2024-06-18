package org.example.hrms.dataAccess.abstracts.CV;

import org.example.hrms.entities.concretes.cv.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduateDao extends JpaRepository<Graduate,Integer> {
}

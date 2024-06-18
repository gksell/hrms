package org.example.hrms.dataAccess.abstracts;

import org.example.hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekersDao extends JpaRepository<JobSeeker, Integer> {
    boolean existsJobSeekerByeMailAddressAndNationalIdentityNumber(String email, String nationalIdentityNumber);
}

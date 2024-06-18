package org.example.hrms.dataAccess.abstracts;

import org.example.hrms.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer,Integer> {
    Employer findEmployerByeMail(String email);
}

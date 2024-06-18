package org.example.hrms.dataAccess.abstracts;

import org.example.hrms.entities.concretes.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobDao extends JpaRepository<Job, Integer> {
    List<Job> findByIsActiveTrue();
    List<Job> findByIsActiveTrueOrderByCreatedDateDesc();
    List<Job> findByEmployerIdAndIsActiveTrue(int companyId);

    //jpql
    // @Query("Select new org.example.hrms.entities.dtos.JobDto(p.Id, p.productName, c.categoryName) From Category c Inner Join c.products p")
}

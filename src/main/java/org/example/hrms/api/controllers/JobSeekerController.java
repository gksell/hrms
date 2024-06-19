package org.example.hrms.api.controllers;

import org.example.hrms.business.abstracts.JobSeekerService;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.concretes.JobSeeker;
import org.example.hrms.entities.dtos.JobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/job_seeker")
public class JobSeekerController {
    private final JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping
    public ResponseEntity<?> addJobSeeker(@RequestBody @Valid JobSeekerDto jobSeekerDto) {
        return ResponseEntity.ok(this.jobSeekerService.add(jobSeekerDto));
    }
}

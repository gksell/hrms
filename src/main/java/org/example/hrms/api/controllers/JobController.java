package org.example.hrms.api.controllers;

import org.example.hrms.business.abstracts.JobService;
import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.dtos.JobDto;
import org.example.hrms.entities.dtos.JobStatusChangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobDto jobDto) {
        return ResponseEntity.ok(this.jobService.add(jobDto));
    }

    @GetMapping("/getallactive")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.jobService.getAllActiveJob());
    }

    @GetMapping("/getallactivedesc")
    public ResponseEntity<?> getAllActiveDesc() {
        return ResponseEntity.ok(this.jobService.getAllActiveJobDesc());
    }

    @PostMapping("toggle_job_status")
    public ResponseEntity<?> toggleJobStatus(@RequestBody JobStatusChangeDto jobStatusChangeDto) {
        return ResponseEntity.ok(this.jobService.toggleJobStatus(jobStatusChangeDto));
    }

    @GetMapping("get_job_by_companyid")
    public ResponseEntity<?> getJobByCompanyId(@RequestParam int companyId) {
        return ResponseEntity.ok(this.jobService.getJobByCompanyId(companyId));
    }
}

package org.example.hrms.api.controllers;

import org.example.hrms.business.abstracts.JobPositionService;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.concretes.JobPosition;
import org.example.hrms.entities.dtos.JobPositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job_positions")
public class JobPositionController {
    private final JobPositionService jobPositionService;

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllJobPositions() {
        return ResponseEntity.ok(this.jobPositionService.getJobPositions());
    }

    @PostMapping("add")
    public ResponseEntity<?>  addJobPosition(@RequestBody JobPositionDto jobPositionDto) {
        return ResponseEntity.ok(this.jobPositionService.add(jobPositionDto));
    }
}

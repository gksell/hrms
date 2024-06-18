package org.example.hrms.api.controllers;

import org.example.hrms.business.abstracts.EmployerService;
import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.concretes.Employer;
import org.example.hrms.entities.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employer")
public class EmployerController {
    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllEmployers() {
        return ResponseEntity.ok(this.employerService.getEmployer());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployer(@RequestBody EmployerDto employerDto) {
        return ResponseEntity.ok(this.employerService.add(employerDto));
    }
}

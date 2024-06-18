package org.example.hrms.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.example.hrms.business.abstracts.CVService;
import org.example.hrms.business.concretes.JobSeekerManager;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.dtos.CV.CVDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cv")
public class CVController {
    private final CVService cvService;
    private final JobSeekerManager jobSeekerManager;

    @Autowired
    public CVController(CVService cvService, JobSeekerManager jobSeekerManager) {
        this.cvService = cvService;
        this.jobSeekerManager = jobSeekerManager;
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add CV", notes = "Add CV details")
    public ResponseEntity<?> addCV(
            @ApiParam(value = "CV details", required = true) @RequestBody CVDto cvDto) {
        return ResponseEntity.ok(this.cvService.add(cvDto));
    }

    @PostMapping("/addPhoto/{cvId}")
    @ApiOperation(value = "Add Photo to CV", notes = "Add a photo to an existing CV by ID")
    public ResponseEntity<?> addPhoto(
            @ApiParam(value = "CV ID", required = true) @PathVariable("cvId") int cvId,
            @ApiParam(value = "Photo file", required = true) @RequestPart("photoFile") MultipartFile photoFile) {
        return ResponseEntity.ok(this.cvService.addPhoto(cvId, photoFile));
    }

    @PostMapping("/getCvsByJobSeekerId")
    public  ResponseEntity<?> getCvsByJobSeekerId(int jobSeekerId) {
        return ResponseEntity.ok(this.cvService.getCvsByJobSeekerId(jobSeekerId));
    }
}

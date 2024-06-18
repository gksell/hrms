package org.example.hrms.business.abstracts;

import org.example.hrms.core.utilities.results.DataResult;
import org.example.hrms.core.utilities.results.Result;
import org.example.hrms.entities.dtos.CV.CVDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CVService {
    Result add(CVDto cvDto);
    Result addPhoto(int id, MultipartFile file);
    DataResult<List<CVDto>> getCvsByJobSeekerId(int jobSeekerId);
}

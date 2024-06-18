package org.example.hrms.business.utils.mappers;


import org.example.hrms.entities.concretes.JobPosition;
import org.example.hrms.entities.dtos.JobPositionDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobPositionMapper {

    public static List<JobPositionDto> mapToJobPositionDtoList(List<JobPosition> jobPositions) {
        if (jobPositions == null) {
            return Collections.emptyList();
        }

        return jobPositions.stream()
                .map(jobPosition -> new JobPositionDto(
                        jobPosition.getId(),
                        jobPosition.getPositionName()
                ))
                .collect(Collectors.toList());
    }

    public static JobPosition mapToJobPosition(JobPositionDto jobPositionDto) {
        if (jobPositionDto == null) {
            return null;
        }

        JobPosition jobPosition = new JobPosition();
        jobPosition.setId(jobPositionDto.getId());
        jobPosition.setPositionName(jobPositionDto.getPositionName());

        return jobPosition;
    }
}

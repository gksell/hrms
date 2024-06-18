package org.example.hrms.entities.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.hrms.entities.abstracts.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "work_experiences")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE graduates SET deleted = true, deleted_date = NOW() WHERE id = ?")
@Where(clause = "deleted = false")
public class WorkExperience extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_experience_id")
    private int id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "job_position")
    private String jobPosition;
    @Column(name = "start_year")
    private LocalDate startYear;
    @Column(name = "end_year")
    private LocalDate endYear;
    @Column(name = "is_end")
    private boolean isEnd;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CV cv;
}

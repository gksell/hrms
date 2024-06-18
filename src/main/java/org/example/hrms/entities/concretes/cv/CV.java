package org.example.hrms.entities.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.hrms.entities.abstracts.BaseEntity;
import org.example.hrms.entities.concretes.JobSeeker;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cvs")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE graduates SET deleted = true, deleted_date = NOW() WHERE id = ?")
@Where(clause = "deleted = false")
public class CV extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cv_id")
    private int id;
    @Column(name = "cover_letter")
    private String coverLetter;
    @Column(name = "github_address")
    private String githubAddress;
    @Column(name = "linkedin_address")
    private String linkedinAddress;
    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @OneToMany(mappedBy = "cv" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills;

    @OneToMany(mappedBy = "cv" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Graduate> graduates;

}

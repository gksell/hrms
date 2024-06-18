package org.example.hrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import org.example.hrms.entities.abstracts.BaseEntity;
import org.example.hrms.entities.concretes.cv.CV;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "job_seekers")
@SQLDelete(sql = "UPDATE job_seekers SET deleted = true, deleted_date = NOW() WHERE id = ?")
@Where(clause = "deleted = false")
public class JobSeeker extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_seeker_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "tcno")
    private String nationalIdentityNumber;
    @Column(name = "birth_year")
    private int birthYear;
    @Column(name = "email")
    private String eMailAddress;
    @Column(name = "password")
    private String password;
    @Column(name = "email_verified")
    private boolean emailVerified;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CV> cvs;
}

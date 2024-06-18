package org.example.hrms.entities.concretes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.hrms.entities.abstracts.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employers")
@Data
@SQLDelete(sql = "UPDATE graduates SET deleted = true, deleted_date = NOW() WHERE id = ?")
@Where(clause = "deleted = false")
public class Employer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private int id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "website")
    private String website;
    @Column(name = "email")
    private String eMail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "approved_by_hrms")
    private boolean approvalByHrms;
    @Column(name = "email_verified")
    private boolean emailVerificationStatus;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;
}

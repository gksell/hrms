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
@Table(name = "graduates")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE graduates SET deleted = true, deleted_date = NOW() WHERE id = ?")
@Where(clause = "deleted = false")
public class Graduate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduate_id")
    private int id;
    @Column(name = "school_name")
    private String schoolName;
    @Column(name = "department")
    private String department;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "graduate_date")
    private LocalDate graduateDate;
    @Column(name = "is_graduate")
    private boolean isGraduate;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CV cv;
}

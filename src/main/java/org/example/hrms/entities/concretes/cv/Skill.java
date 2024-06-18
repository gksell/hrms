package org.example.hrms.entities.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.hrms.entities.abstracts.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "skills")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE graduates SET deleted = true, deleted_date = NOW() WHERE id = ?")
@Where(clause = "deleted = false")
public class Skill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int id;
    @Column(name = "skill_name")
    private String skillName;
    @Column(name = "level")
    private byte level;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CV cv;
}

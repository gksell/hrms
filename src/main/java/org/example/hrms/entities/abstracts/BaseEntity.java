package org.example.hrms.entities.abstracts;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name ="deleted", nullable = false)
    private boolean deleted;

    @Column(name = "created_date", nullable = false,updatable = false)
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "deleted_date")
    private LocalDate deletedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (!deleted) {
            updatedDate = LocalDate.now();
        }
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
        if (deleted) {
            this.deletedDate = LocalDate.now();
        }
    }
}

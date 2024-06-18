package org.example.hrms.dataAccess.abstracts.CV;

import org.example.hrms.entities.concretes.cv.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language, Integer> {
}

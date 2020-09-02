package com.gok.repository;

import com.gok.domain.MedicalPractitioner;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalPractitioner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalPractitionerRepository extends JpaRepository<MedicalPractitioner, Long> {
}

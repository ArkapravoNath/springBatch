package com.gok.service;

import com.gok.service.dto.MedicalPractitionerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.domain.MedicalPractitioner}.
 */
public interface MedicalPractitionerService {

    /**
     * Save a medicalPractitioner.
     *
     * @param medicalPractitionerDTO the entity to save.
     * @return the persisted entity.
     */
    MedicalPractitionerDTO save(MedicalPractitionerDTO medicalPractitionerDTO);

    /**
     * Get all the medicalPractitioners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MedicalPractitionerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" medicalPractitioner.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedicalPractitionerDTO> findOne(Long id);

    /**
     * Delete the "id" medicalPractitioner.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.gok.service;

import com.gok.service.dto.PractitionerScheduleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.domain.PractitionerSchedule}.
 */
public interface PractitionerScheduleService {

    /**
     * Save a practitionerSchedule.
     *
     * @param practitionerScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    PractitionerScheduleDTO save(PractitionerScheduleDTO practitionerScheduleDTO);

    /**
     * Get all the practitionerSchedules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PractitionerScheduleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" practitionerSchedule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PractitionerScheduleDTO> findOne(Long id);

    /**
     * Delete the "id" practitionerSchedule.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

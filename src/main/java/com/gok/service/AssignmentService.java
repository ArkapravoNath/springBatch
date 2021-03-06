package com.gok.service;

import com.gok.service.dto.AssignmentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.domain.Assignment}.
 */
public interface AssignmentService {

    /**
     * Save a assignment.
     *
     * @param assignmentDTO the entity to save.
     * @return the persisted entity.
     */
    AssignmentDTO save(AssignmentDTO assignmentDTO);

    /**
     * Get all the assignments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssignmentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" assignment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssignmentDTO> findOne(Long id);

    /**
     * Delete the "id" assignment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

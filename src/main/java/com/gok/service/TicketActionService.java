package com.gok.service;

import com.gok.service.dto.TicketActionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.domain.TicketAction}.
 */
public interface TicketActionService {

    /**
     * Save a ticketAction.
     *
     * @param ticketActionDTO the entity to save.
     * @return the persisted entity.
     */
    TicketActionDTO save(TicketActionDTO ticketActionDTO);

    /**
     * Get all the ticketActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TicketActionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ticketAction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TicketActionDTO> findOne(Long id);

    /**
     * Delete the "id" ticketAction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

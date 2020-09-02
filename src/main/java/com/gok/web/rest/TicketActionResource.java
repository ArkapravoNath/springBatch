package com.gok.web.rest;

import com.gok.service.TicketActionService;
import com.gok.web.rest.errors.BadRequestAlertException;
import com.gok.service.dto.TicketActionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gok.domain.TicketAction}.
 */
@RestController
@RequestMapping("/api")
public class TicketActionResource {

    private final Logger log = LoggerFactory.getLogger(TicketActionResource.class);

    private static final String ENTITY_NAME = "springBatchTicketAction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TicketActionService ticketActionService;

    public TicketActionResource(TicketActionService ticketActionService) {
        this.ticketActionService = ticketActionService;
    }

    /**
     * {@code POST  /ticket-actions} : Create a new ticketAction.
     *
     * @param ticketActionDTO the ticketActionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ticketActionDTO, or with status {@code 400 (Bad Request)} if the ticketAction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ticket-actions")
    public ResponseEntity<TicketActionDTO> createTicketAction(@RequestBody TicketActionDTO ticketActionDTO) throws URISyntaxException {
        log.debug("REST request to save TicketAction : {}", ticketActionDTO);
        if (ticketActionDTO.getId() != null) {
            throw new BadRequestAlertException("A new ticketAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TicketActionDTO result = ticketActionService.save(ticketActionDTO);
        return ResponseEntity.created(new URI("/api/ticket-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ticket-actions} : Updates an existing ticketAction.
     *
     * @param ticketActionDTO the ticketActionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketActionDTO,
     * or with status {@code 400 (Bad Request)} if the ticketActionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ticketActionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ticket-actions")
    public ResponseEntity<TicketActionDTO> updateTicketAction(@RequestBody TicketActionDTO ticketActionDTO) throws URISyntaxException {
        log.debug("REST request to update TicketAction : {}", ticketActionDTO);
        if (ticketActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TicketActionDTO result = ticketActionService.save(ticketActionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ticketActionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ticket-actions} : get all the ticketActions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ticketActions in body.
     */
    @GetMapping("/ticket-actions")
    public ResponseEntity<List<TicketActionDTO>> getAllTicketActions(Pageable pageable) {
        log.debug("REST request to get a page of TicketActions");
        Page<TicketActionDTO> page = ticketActionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ticket-actions/:id} : get the "id" ticketAction.
     *
     * @param id the id of the ticketActionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ticketActionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ticket-actions/{id}")
    public ResponseEntity<TicketActionDTO> getTicketAction(@PathVariable Long id) {
        log.debug("REST request to get TicketAction : {}", id);
        Optional<TicketActionDTO> ticketActionDTO = ticketActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ticketActionDTO);
    }

    /**
     * {@code DELETE  /ticket-actions/:id} : delete the "id" ticketAction.
     *
     * @param id the id of the ticketActionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ticket-actions/{id}")
    public ResponseEntity<Void> deleteTicketAction(@PathVariable Long id) {
        log.debug("REST request to delete TicketAction : {}", id);
        ticketActionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

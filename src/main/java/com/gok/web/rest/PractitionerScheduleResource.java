package com.gok.web.rest;

import com.gok.service.PractitionerScheduleService;
import com.gok.web.rest.errors.BadRequestAlertException;
import com.gok.service.dto.PractitionerScheduleDTO;

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
 * REST controller for managing {@link com.gok.domain.PractitionerSchedule}.
 */
@RestController
@RequestMapping("/api")
public class PractitionerScheduleResource {

    private final Logger log = LoggerFactory.getLogger(PractitionerScheduleResource.class);

    private static final String ENTITY_NAME = "springBatchPractitionerSchedule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PractitionerScheduleService practitionerScheduleService;

    public PractitionerScheduleResource(PractitionerScheduleService practitionerScheduleService) {
        this.practitionerScheduleService = practitionerScheduleService;
    }

    /**
     * {@code POST  /practitioner-schedules} : Create a new practitionerSchedule.
     *
     * @param practitionerScheduleDTO the practitionerScheduleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new practitionerScheduleDTO, or with status {@code 400 (Bad Request)} if the practitionerSchedule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/practitioner-schedules")
    public ResponseEntity<PractitionerScheduleDTO> createPractitionerSchedule(@RequestBody PractitionerScheduleDTO practitionerScheduleDTO) throws URISyntaxException {
        log.debug("REST request to save PractitionerSchedule : {}", practitionerScheduleDTO);
        if (practitionerScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new practitionerSchedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PractitionerScheduleDTO result = practitionerScheduleService.save(practitionerScheduleDTO);
        return ResponseEntity.created(new URI("/api/practitioner-schedules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /practitioner-schedules} : Updates an existing practitionerSchedule.
     *
     * @param practitionerScheduleDTO the practitionerScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated practitionerScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the practitionerScheduleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the practitionerScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/practitioner-schedules")
    public ResponseEntity<PractitionerScheduleDTO> updatePractitionerSchedule(@RequestBody PractitionerScheduleDTO practitionerScheduleDTO) throws URISyntaxException {
        log.debug("REST request to update PractitionerSchedule : {}", practitionerScheduleDTO);
        if (practitionerScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PractitionerScheduleDTO result = practitionerScheduleService.save(practitionerScheduleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, practitionerScheduleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /practitioner-schedules} : get all the practitionerSchedules.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of practitionerSchedules in body.
     */
    @GetMapping("/practitioner-schedules")
    public ResponseEntity<List<PractitionerScheduleDTO>> getAllPractitionerSchedules(Pageable pageable) {
        log.debug("REST request to get a page of PractitionerSchedules");
        Page<PractitionerScheduleDTO> page = practitionerScheduleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /practitioner-schedules/:id} : get the "id" practitionerSchedule.
     *
     * @param id the id of the practitionerScheduleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the practitionerScheduleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/practitioner-schedules/{id}")
    public ResponseEntity<PractitionerScheduleDTO> getPractitionerSchedule(@PathVariable Long id) {
        log.debug("REST request to get PractitionerSchedule : {}", id);
        Optional<PractitionerScheduleDTO> practitionerScheduleDTO = practitionerScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(practitionerScheduleDTO);
    }

    /**
     * {@code DELETE  /practitioner-schedules/:id} : delete the "id" practitionerSchedule.
     *
     * @param id the id of the practitionerScheduleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/practitioner-schedules/{id}")
    public ResponseEntity<Void> deletePractitionerSchedule(@PathVariable Long id) {
        log.debug("REST request to delete PractitionerSchedule : {}", id);
        practitionerScheduleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

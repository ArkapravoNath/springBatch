package com.gok.web.rest;

import com.gok.service.MedicalPractitionerService;
import com.gok.web.rest.errors.BadRequestAlertException;
import com.gok.service.dto.MedicalPractitionerDTO;

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
 * REST controller for managing {@link com.gok.domain.MedicalPractitioner}.
 */
@RestController
@RequestMapping("/api")
public class MedicalPractitionerResource {

    private final Logger log = LoggerFactory.getLogger(MedicalPractitionerResource.class);

    private static final String ENTITY_NAME = "springBatchMedicalPractitioner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalPractitionerService medicalPractitionerService;

    public MedicalPractitionerResource(MedicalPractitionerService medicalPractitionerService) {
        this.medicalPractitionerService = medicalPractitionerService;
    }

    /**
     * {@code POST  /medical-practitioners} : Create a new medicalPractitioner.
     *
     * @param medicalPractitionerDTO the medicalPractitionerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalPractitionerDTO, or with status {@code 400 (Bad Request)} if the medicalPractitioner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-practitioners")
    public ResponseEntity<MedicalPractitionerDTO> createMedicalPractitioner(@RequestBody MedicalPractitionerDTO medicalPractitionerDTO) throws URISyntaxException {
        log.debug("REST request to save MedicalPractitioner : {}", medicalPractitionerDTO);
        if (medicalPractitionerDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicalPractitioner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalPractitionerDTO result = medicalPractitionerService.save(medicalPractitionerDTO);
        return ResponseEntity.created(new URI("/api/medical-practitioners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-practitioners} : Updates an existing medicalPractitioner.
     *
     * @param medicalPractitionerDTO the medicalPractitionerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalPractitionerDTO,
     * or with status {@code 400 (Bad Request)} if the medicalPractitionerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalPractitionerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-practitioners")
    public ResponseEntity<MedicalPractitionerDTO> updateMedicalPractitioner(@RequestBody MedicalPractitionerDTO medicalPractitionerDTO) throws URISyntaxException {
        log.debug("REST request to update MedicalPractitioner : {}", medicalPractitionerDTO);
        if (medicalPractitionerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalPractitionerDTO result = medicalPractitionerService.save(medicalPractitionerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medicalPractitionerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-practitioners} : get all the medicalPractitioners.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalPractitioners in body.
     */
    @GetMapping("/medical-practitioners")
    public ResponseEntity<List<MedicalPractitionerDTO>> getAllMedicalPractitioners(Pageable pageable) {
        log.debug("REST request to get a page of MedicalPractitioners");
        Page<MedicalPractitionerDTO> page = medicalPractitionerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-practitioners/:id} : get the "id" medicalPractitioner.
     *
     * @param id the id of the medicalPractitionerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalPractitionerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-practitioners/{id}")
    public ResponseEntity<MedicalPractitionerDTO> getMedicalPractitioner(@PathVariable Long id) {
        log.debug("REST request to get MedicalPractitioner : {}", id);
        Optional<MedicalPractitionerDTO> medicalPractitionerDTO = medicalPractitionerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalPractitionerDTO);
    }

    /**
     * {@code DELETE  /medical-practitioners/:id} : delete the "id" medicalPractitioner.
     *
     * @param id the id of the medicalPractitionerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-practitioners/{id}")
    public ResponseEntity<Void> deleteMedicalPractitioner(@PathVariable Long id) {
        log.debug("REST request to delete MedicalPractitioner : {}", id);
        medicalPractitionerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

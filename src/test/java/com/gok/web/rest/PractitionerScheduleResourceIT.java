package com.gok.web.rest;

import com.gok.SpringBatchApp;
import com.gok.config.TestSecurityConfiguration;
import com.gok.domain.PractitionerSchedule;
import com.gok.repository.PractitionerScheduleRepository;
import com.gok.service.PractitionerScheduleService;
import com.gok.service.dto.PractitionerScheduleDTO;
import com.gok.service.mapper.PractitionerScheduleMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PractitionerScheduleResource} REST controller.
 */
@SpringBootTest(classes = { SpringBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class PractitionerScheduleResourceIT {

    private static final String DEFAULT_TYPE_OF_SCHEDULE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_SCHEDULE = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SHIFT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SHIFT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_SCHEDULE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SCHEDULE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SCHEDULE_START_TIME = 1;
    private static final Integer UPDATED_SCHEDULE_START_TIME = 2;

    private static final Integer DEFAULT_SCHEDULE_END_TIME = 1;
    private static final Integer UPDATED_SCHEDULE_END_TIME = 2;

    @Autowired
    private PractitionerScheduleRepository practitionerScheduleRepository;

    @Autowired
    private PractitionerScheduleMapper practitionerScheduleMapper;

    @Autowired
    private PractitionerScheduleService practitionerScheduleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPractitionerScheduleMockMvc;

    private PractitionerSchedule practitionerSchedule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PractitionerSchedule createEntity(EntityManager em) {
        PractitionerSchedule practitionerSchedule = new PractitionerSchedule()
            .typeOfSchedule(DEFAULT_TYPE_OF_SCHEDULE)
            .startDateTime(DEFAULT_START_DATE_TIME)
            .endDateTime(DEFAULT_END_DATE_TIME)
            .shiftType(DEFAULT_SHIFT_TYPE)
            .status(DEFAULT_STATUS)
            .scheduleDate(DEFAULT_SCHEDULE_DATE)
            .scheduleStartTime(DEFAULT_SCHEDULE_START_TIME)
            .scheduleEndTime(DEFAULT_SCHEDULE_END_TIME);
        return practitionerSchedule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PractitionerSchedule createUpdatedEntity(EntityManager em) {
        PractitionerSchedule practitionerSchedule = new PractitionerSchedule()
            .typeOfSchedule(UPDATED_TYPE_OF_SCHEDULE)
            .startDateTime(UPDATED_START_DATE_TIME)
            .endDateTime(UPDATED_END_DATE_TIME)
            .shiftType(UPDATED_SHIFT_TYPE)
            .status(UPDATED_STATUS)
            .scheduleDate(UPDATED_SCHEDULE_DATE)
            .scheduleStartTime(UPDATED_SCHEDULE_START_TIME)
            .scheduleEndTime(UPDATED_SCHEDULE_END_TIME);
        return practitionerSchedule;
    }

    @BeforeEach
    public void initTest() {
        practitionerSchedule = createEntity(em);
    }

    @Test
    @Transactional
    public void createPractitionerSchedule() throws Exception {
        int databaseSizeBeforeCreate = practitionerScheduleRepository.findAll().size();
        // Create the PractitionerSchedule
        PractitionerScheduleDTO practitionerScheduleDTO = practitionerScheduleMapper.toDto(practitionerSchedule);
        restPractitionerScheduleMockMvc.perform(post("/api/practitioner-schedules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(practitionerScheduleDTO)))
            .andExpect(status().isCreated());

        // Validate the PractitionerSchedule in the database
        List<PractitionerSchedule> practitionerScheduleList = practitionerScheduleRepository.findAll();
        assertThat(practitionerScheduleList).hasSize(databaseSizeBeforeCreate + 1);
        PractitionerSchedule testPractitionerSchedule = practitionerScheduleList.get(practitionerScheduleList.size() - 1);
        assertThat(testPractitionerSchedule.getTypeOfSchedule()).isEqualTo(DEFAULT_TYPE_OF_SCHEDULE);
        assertThat(testPractitionerSchedule.getStartDateTime()).isEqualTo(DEFAULT_START_DATE_TIME);
        assertThat(testPractitionerSchedule.getEndDateTime()).isEqualTo(DEFAULT_END_DATE_TIME);
        assertThat(testPractitionerSchedule.getShiftType()).isEqualTo(DEFAULT_SHIFT_TYPE);
        assertThat(testPractitionerSchedule.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPractitionerSchedule.getScheduleDate()).isEqualTo(DEFAULT_SCHEDULE_DATE);
        assertThat(testPractitionerSchedule.getScheduleStartTime()).isEqualTo(DEFAULT_SCHEDULE_START_TIME);
        assertThat(testPractitionerSchedule.getScheduleEndTime()).isEqualTo(DEFAULT_SCHEDULE_END_TIME);
    }

    @Test
    @Transactional
    public void createPractitionerScheduleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = practitionerScheduleRepository.findAll().size();

        // Create the PractitionerSchedule with an existing ID
        practitionerSchedule.setId(1L);
        PractitionerScheduleDTO practitionerScheduleDTO = practitionerScheduleMapper.toDto(practitionerSchedule);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPractitionerScheduleMockMvc.perform(post("/api/practitioner-schedules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(practitionerScheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PractitionerSchedule in the database
        List<PractitionerSchedule> practitionerScheduleList = practitionerScheduleRepository.findAll();
        assertThat(practitionerScheduleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPractitionerSchedules() throws Exception {
        // Initialize the database
        practitionerScheduleRepository.saveAndFlush(practitionerSchedule);

        // Get all the practitionerScheduleList
        restPractitionerScheduleMockMvc.perform(get("/api/practitioner-schedules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(practitionerSchedule.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeOfSchedule").value(hasItem(DEFAULT_TYPE_OF_SCHEDULE)))
            .andExpect(jsonPath("$.[*].startDateTime").value(hasItem(DEFAULT_START_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].endDateTime").value(hasItem(DEFAULT_END_DATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].shiftType").value(hasItem(DEFAULT_SHIFT_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].scheduleDate").value(hasItem(DEFAULT_SCHEDULE_DATE.toString())))
            .andExpect(jsonPath("$.[*].scheduleStartTime").value(hasItem(DEFAULT_SCHEDULE_START_TIME)))
            .andExpect(jsonPath("$.[*].scheduleEndTime").value(hasItem(DEFAULT_SCHEDULE_END_TIME)));
    }
    
    @Test
    @Transactional
    public void getPractitionerSchedule() throws Exception {
        // Initialize the database
        practitionerScheduleRepository.saveAndFlush(practitionerSchedule);

        // Get the practitionerSchedule
        restPractitionerScheduleMockMvc.perform(get("/api/practitioner-schedules/{id}", practitionerSchedule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(practitionerSchedule.getId().intValue()))
            .andExpect(jsonPath("$.typeOfSchedule").value(DEFAULT_TYPE_OF_SCHEDULE))
            .andExpect(jsonPath("$.startDateTime").value(DEFAULT_START_DATE_TIME.toString()))
            .andExpect(jsonPath("$.endDateTime").value(DEFAULT_END_DATE_TIME.toString()))
            .andExpect(jsonPath("$.shiftType").value(DEFAULT_SHIFT_TYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.scheduleDate").value(DEFAULT_SCHEDULE_DATE.toString()))
            .andExpect(jsonPath("$.scheduleStartTime").value(DEFAULT_SCHEDULE_START_TIME))
            .andExpect(jsonPath("$.scheduleEndTime").value(DEFAULT_SCHEDULE_END_TIME));
    }
    @Test
    @Transactional
    public void getNonExistingPractitionerSchedule() throws Exception {
        // Get the practitionerSchedule
        restPractitionerScheduleMockMvc.perform(get("/api/practitioner-schedules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePractitionerSchedule() throws Exception {
        // Initialize the database
        practitionerScheduleRepository.saveAndFlush(practitionerSchedule);

        int databaseSizeBeforeUpdate = practitionerScheduleRepository.findAll().size();

        // Update the practitionerSchedule
        PractitionerSchedule updatedPractitionerSchedule = practitionerScheduleRepository.findById(practitionerSchedule.getId()).get();
        // Disconnect from session so that the updates on updatedPractitionerSchedule are not directly saved in db
        em.detach(updatedPractitionerSchedule);
        updatedPractitionerSchedule
            .typeOfSchedule(UPDATED_TYPE_OF_SCHEDULE)
            .startDateTime(UPDATED_START_DATE_TIME)
            .endDateTime(UPDATED_END_DATE_TIME)
            .shiftType(UPDATED_SHIFT_TYPE)
            .status(UPDATED_STATUS)
            .scheduleDate(UPDATED_SCHEDULE_DATE)
            .scheduleStartTime(UPDATED_SCHEDULE_START_TIME)
            .scheduleEndTime(UPDATED_SCHEDULE_END_TIME);
        PractitionerScheduleDTO practitionerScheduleDTO = practitionerScheduleMapper.toDto(updatedPractitionerSchedule);

        restPractitionerScheduleMockMvc.perform(put("/api/practitioner-schedules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(practitionerScheduleDTO)))
            .andExpect(status().isOk());

        // Validate the PractitionerSchedule in the database
        List<PractitionerSchedule> practitionerScheduleList = practitionerScheduleRepository.findAll();
        assertThat(practitionerScheduleList).hasSize(databaseSizeBeforeUpdate);
        PractitionerSchedule testPractitionerSchedule = practitionerScheduleList.get(practitionerScheduleList.size() - 1);
        assertThat(testPractitionerSchedule.getTypeOfSchedule()).isEqualTo(UPDATED_TYPE_OF_SCHEDULE);
        assertThat(testPractitionerSchedule.getStartDateTime()).isEqualTo(UPDATED_START_DATE_TIME);
        assertThat(testPractitionerSchedule.getEndDateTime()).isEqualTo(UPDATED_END_DATE_TIME);
        assertThat(testPractitionerSchedule.getShiftType()).isEqualTo(UPDATED_SHIFT_TYPE);
        assertThat(testPractitionerSchedule.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPractitionerSchedule.getScheduleDate()).isEqualTo(UPDATED_SCHEDULE_DATE);
        assertThat(testPractitionerSchedule.getScheduleStartTime()).isEqualTo(UPDATED_SCHEDULE_START_TIME);
        assertThat(testPractitionerSchedule.getScheduleEndTime()).isEqualTo(UPDATED_SCHEDULE_END_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingPractitionerSchedule() throws Exception {
        int databaseSizeBeforeUpdate = practitionerScheduleRepository.findAll().size();

        // Create the PractitionerSchedule
        PractitionerScheduleDTO practitionerScheduleDTO = practitionerScheduleMapper.toDto(practitionerSchedule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPractitionerScheduleMockMvc.perform(put("/api/practitioner-schedules").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(practitionerScheduleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PractitionerSchedule in the database
        List<PractitionerSchedule> practitionerScheduleList = practitionerScheduleRepository.findAll();
        assertThat(practitionerScheduleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePractitionerSchedule() throws Exception {
        // Initialize the database
        practitionerScheduleRepository.saveAndFlush(practitionerSchedule);

        int databaseSizeBeforeDelete = practitionerScheduleRepository.findAll().size();

        // Delete the practitionerSchedule
        restPractitionerScheduleMockMvc.perform(delete("/api/practitioner-schedules/{id}", practitionerSchedule.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PractitionerSchedule> practitionerScheduleList = practitionerScheduleRepository.findAll();
        assertThat(practitionerScheduleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

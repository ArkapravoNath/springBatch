package com.gok.web.rest;

import com.gok.SpringBatchApp;
import com.gok.config.TestSecurityConfiguration;
import com.gok.domain.MedicalPractitioner;
import com.gok.repository.MedicalPractitionerRepository;
import com.gok.service.MedicalPractitionerService;
import com.gok.service.dto.MedicalPractitionerDTO;
import com.gok.service.mapper.MedicalPractitionerMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MedicalPractitionerResource} REST controller.
 */
@SpringBootTest(classes = { SpringBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class MedicalPractitionerResourceIT {

    private static final String DEFAULT_PRACTITIONER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRACTITIONER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIALTY = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALTY = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYMENT_MODE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYMENT_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_QUALIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_QUALIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_PERSON_ID_REF = 1L;
    private static final Long UPDATED_PERSON_ID_REF = 2L;

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_WHATSAPP_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WHATSAPP_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_1 = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_2 = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_EXIST = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_EXIST = "BBBBBBBBBB";

    @Autowired
    private MedicalPractitionerRepository medicalPractitionerRepository;

    @Autowired
    private MedicalPractitionerMapper medicalPractitionerMapper;

    @Autowired
    private MedicalPractitionerService medicalPractitionerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalPractitionerMockMvc;

    private MedicalPractitioner medicalPractitioner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalPractitioner createEntity(EntityManager em) {
        MedicalPractitioner medicalPractitioner = new MedicalPractitioner()
            .practitionerType(DEFAULT_PRACTITIONER_TYPE)
            .specialty(DEFAULT_SPECIALTY)
            .registrationNumber(DEFAULT_REGISTRATION_NUMBER)
            .employmentMode(DEFAULT_EMPLOYMENT_MODE)
            .qualification(DEFAULT_QUALIFICATION)
            .status(DEFAULT_STATUS)
            .personIdRef(DEFAULT_PERSON_ID_REF)
            .category(DEFAULT_CATEGORY)
            .position(DEFAULT_POSITION)
            .whatsappNumber(DEFAULT_WHATSAPP_NUMBER)
            .email(DEFAULT_EMAIL)
            .language1(DEFAULT_LANGUAGE_1)
            .language2(DEFAULT_LANGUAGE_2)
            .vehicleExist(DEFAULT_VEHICLE_EXIST);
        return medicalPractitioner;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalPractitioner createUpdatedEntity(EntityManager em) {
        MedicalPractitioner medicalPractitioner = new MedicalPractitioner()
            .practitionerType(UPDATED_PRACTITIONER_TYPE)
            .specialty(UPDATED_SPECIALTY)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .employmentMode(UPDATED_EMPLOYMENT_MODE)
            .qualification(UPDATED_QUALIFICATION)
            .status(UPDATED_STATUS)
            .personIdRef(UPDATED_PERSON_ID_REF)
            .category(UPDATED_CATEGORY)
            .position(UPDATED_POSITION)
            .whatsappNumber(UPDATED_WHATSAPP_NUMBER)
            .email(UPDATED_EMAIL)
            .language1(UPDATED_LANGUAGE_1)
            .language2(UPDATED_LANGUAGE_2)
            .vehicleExist(UPDATED_VEHICLE_EXIST);
        return medicalPractitioner;
    }

    @BeforeEach
    public void initTest() {
        medicalPractitioner = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalPractitioner() throws Exception {
        int databaseSizeBeforeCreate = medicalPractitionerRepository.findAll().size();
        // Create the MedicalPractitioner
        MedicalPractitionerDTO medicalPractitionerDTO = medicalPractitionerMapper.toDto(medicalPractitioner);
        restMedicalPractitionerMockMvc.perform(post("/api/medical-practitioners").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalPractitionerDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalPractitioner in the database
        List<MedicalPractitioner> medicalPractitionerList = medicalPractitionerRepository.findAll();
        assertThat(medicalPractitionerList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalPractitioner testMedicalPractitioner = medicalPractitionerList.get(medicalPractitionerList.size() - 1);
        assertThat(testMedicalPractitioner.getPractitionerType()).isEqualTo(DEFAULT_PRACTITIONER_TYPE);
        assertThat(testMedicalPractitioner.getSpecialty()).isEqualTo(DEFAULT_SPECIALTY);
        assertThat(testMedicalPractitioner.getRegistrationNumber()).isEqualTo(DEFAULT_REGISTRATION_NUMBER);
        assertThat(testMedicalPractitioner.getEmploymentMode()).isEqualTo(DEFAULT_EMPLOYMENT_MODE);
        assertThat(testMedicalPractitioner.getQualification()).isEqualTo(DEFAULT_QUALIFICATION);
        assertThat(testMedicalPractitioner.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMedicalPractitioner.getPersonIdRef()).isEqualTo(DEFAULT_PERSON_ID_REF);
        assertThat(testMedicalPractitioner.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testMedicalPractitioner.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testMedicalPractitioner.getWhatsappNumber()).isEqualTo(DEFAULT_WHATSAPP_NUMBER);
        assertThat(testMedicalPractitioner.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMedicalPractitioner.getLanguage1()).isEqualTo(DEFAULT_LANGUAGE_1);
        assertThat(testMedicalPractitioner.getLanguage2()).isEqualTo(DEFAULT_LANGUAGE_2);
        assertThat(testMedicalPractitioner.getVehicleExist()).isEqualTo(DEFAULT_VEHICLE_EXIST);
    }

    @Test
    @Transactional
    public void createMedicalPractitionerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalPractitionerRepository.findAll().size();

        // Create the MedicalPractitioner with an existing ID
        medicalPractitioner.setId(1L);
        MedicalPractitionerDTO medicalPractitionerDTO = medicalPractitionerMapper.toDto(medicalPractitioner);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalPractitionerMockMvc.perform(post("/api/medical-practitioners").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalPractitionerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalPractitioner in the database
        List<MedicalPractitioner> medicalPractitionerList = medicalPractitionerRepository.findAll();
        assertThat(medicalPractitionerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalPractitioners() throws Exception {
        // Initialize the database
        medicalPractitionerRepository.saveAndFlush(medicalPractitioner);

        // Get all the medicalPractitionerList
        restMedicalPractitionerMockMvc.perform(get("/api/medical-practitioners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalPractitioner.getId().intValue())))
            .andExpect(jsonPath("$.[*].practitionerType").value(hasItem(DEFAULT_PRACTITIONER_TYPE)))
            .andExpect(jsonPath("$.[*].specialty").value(hasItem(DEFAULT_SPECIALTY)))
            .andExpect(jsonPath("$.[*].registrationNumber").value(hasItem(DEFAULT_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].employmentMode").value(hasItem(DEFAULT_EMPLOYMENT_MODE)))
            .andExpect(jsonPath("$.[*].qualification").value(hasItem(DEFAULT_QUALIFICATION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].personIdRef").value(hasItem(DEFAULT_PERSON_ID_REF.intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].whatsappNumber").value(hasItem(DEFAULT_WHATSAPP_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].language1").value(hasItem(DEFAULT_LANGUAGE_1)))
            .andExpect(jsonPath("$.[*].language2").value(hasItem(DEFAULT_LANGUAGE_2)))
            .andExpect(jsonPath("$.[*].vehicleExist").value(hasItem(DEFAULT_VEHICLE_EXIST)));
    }
    
    @Test
    @Transactional
    public void getMedicalPractitioner() throws Exception {
        // Initialize the database
        medicalPractitionerRepository.saveAndFlush(medicalPractitioner);

        // Get the medicalPractitioner
        restMedicalPractitionerMockMvc.perform(get("/api/medical-practitioners/{id}", medicalPractitioner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalPractitioner.getId().intValue()))
            .andExpect(jsonPath("$.practitionerType").value(DEFAULT_PRACTITIONER_TYPE))
            .andExpect(jsonPath("$.specialty").value(DEFAULT_SPECIALTY))
            .andExpect(jsonPath("$.registrationNumber").value(DEFAULT_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.employmentMode").value(DEFAULT_EMPLOYMENT_MODE))
            .andExpect(jsonPath("$.qualification").value(DEFAULT_QUALIFICATION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.personIdRef").value(DEFAULT_PERSON_ID_REF.intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.whatsappNumber").value(DEFAULT_WHATSAPP_NUMBER))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.language1").value(DEFAULT_LANGUAGE_1))
            .andExpect(jsonPath("$.language2").value(DEFAULT_LANGUAGE_2))
            .andExpect(jsonPath("$.vehicleExist").value(DEFAULT_VEHICLE_EXIST));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalPractitioner() throws Exception {
        // Get the medicalPractitioner
        restMedicalPractitionerMockMvc.perform(get("/api/medical-practitioners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalPractitioner() throws Exception {
        // Initialize the database
        medicalPractitionerRepository.saveAndFlush(medicalPractitioner);

        int databaseSizeBeforeUpdate = medicalPractitionerRepository.findAll().size();

        // Update the medicalPractitioner
        MedicalPractitioner updatedMedicalPractitioner = medicalPractitionerRepository.findById(medicalPractitioner.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalPractitioner are not directly saved in db
        em.detach(updatedMedicalPractitioner);
        updatedMedicalPractitioner
            .practitionerType(UPDATED_PRACTITIONER_TYPE)
            .specialty(UPDATED_SPECIALTY)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .employmentMode(UPDATED_EMPLOYMENT_MODE)
            .qualification(UPDATED_QUALIFICATION)
            .status(UPDATED_STATUS)
            .personIdRef(UPDATED_PERSON_ID_REF)
            .category(UPDATED_CATEGORY)
            .position(UPDATED_POSITION)
            .whatsappNumber(UPDATED_WHATSAPP_NUMBER)
            .email(UPDATED_EMAIL)
            .language1(UPDATED_LANGUAGE_1)
            .language2(UPDATED_LANGUAGE_2)
            .vehicleExist(UPDATED_VEHICLE_EXIST);
        MedicalPractitionerDTO medicalPractitionerDTO = medicalPractitionerMapper.toDto(updatedMedicalPractitioner);

        restMedicalPractitionerMockMvc.perform(put("/api/medical-practitioners").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalPractitionerDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalPractitioner in the database
        List<MedicalPractitioner> medicalPractitionerList = medicalPractitionerRepository.findAll();
        assertThat(medicalPractitionerList).hasSize(databaseSizeBeforeUpdate);
        MedicalPractitioner testMedicalPractitioner = medicalPractitionerList.get(medicalPractitionerList.size() - 1);
        assertThat(testMedicalPractitioner.getPractitionerType()).isEqualTo(UPDATED_PRACTITIONER_TYPE);
        assertThat(testMedicalPractitioner.getSpecialty()).isEqualTo(UPDATED_SPECIALTY);
        assertThat(testMedicalPractitioner.getRegistrationNumber()).isEqualTo(UPDATED_REGISTRATION_NUMBER);
        assertThat(testMedicalPractitioner.getEmploymentMode()).isEqualTo(UPDATED_EMPLOYMENT_MODE);
        assertThat(testMedicalPractitioner.getQualification()).isEqualTo(UPDATED_QUALIFICATION);
        assertThat(testMedicalPractitioner.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMedicalPractitioner.getPersonIdRef()).isEqualTo(UPDATED_PERSON_ID_REF);
        assertThat(testMedicalPractitioner.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testMedicalPractitioner.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testMedicalPractitioner.getWhatsappNumber()).isEqualTo(UPDATED_WHATSAPP_NUMBER);
        assertThat(testMedicalPractitioner.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMedicalPractitioner.getLanguage1()).isEqualTo(UPDATED_LANGUAGE_1);
        assertThat(testMedicalPractitioner.getLanguage2()).isEqualTo(UPDATED_LANGUAGE_2);
        assertThat(testMedicalPractitioner.getVehicleExist()).isEqualTo(UPDATED_VEHICLE_EXIST);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalPractitioner() throws Exception {
        int databaseSizeBeforeUpdate = medicalPractitionerRepository.findAll().size();

        // Create the MedicalPractitioner
        MedicalPractitionerDTO medicalPractitionerDTO = medicalPractitionerMapper.toDto(medicalPractitioner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalPractitionerMockMvc.perform(put("/api/medical-practitioners").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalPractitionerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalPractitioner in the database
        List<MedicalPractitioner> medicalPractitionerList = medicalPractitionerRepository.findAll();
        assertThat(medicalPractitionerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalPractitioner() throws Exception {
        // Initialize the database
        medicalPractitionerRepository.saveAndFlush(medicalPractitioner);

        int databaseSizeBeforeDelete = medicalPractitionerRepository.findAll().size();

        // Delete the medicalPractitioner
        restMedicalPractitionerMockMvc.perform(delete("/api/medical-practitioners/{id}", medicalPractitioner.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalPractitioner> medicalPractitionerList = medicalPractitionerRepository.findAll();
        assertThat(medicalPractitionerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

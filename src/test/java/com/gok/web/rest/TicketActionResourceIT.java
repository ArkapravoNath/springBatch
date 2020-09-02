package com.gok.web.rest;

import com.gok.SpringBatchApp;
import com.gok.config.TestSecurityConfiguration;
import com.gok.domain.TicketAction;
import com.gok.repository.TicketActionRepository;
import com.gok.service.TicketActionService;
import com.gok.service.dto.TicketActionDTO;
import com.gok.service.mapper.TicketActionMapper;

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
 * Integration tests for the {@link TicketActionResource} REST controller.
 */
@SpringBootTest(classes = { SpringBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class TicketActionResourceIT {

    private static final String DEFAULT_ACTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIONS = "AAAAAAAAAA";
    private static final String UPDATED_ACTIONS = "BBBBBBBBBB";

    private static final Long DEFAULT_ACTION_TAKEN_BY_REF = 1L;
    private static final Long UPDATED_ACTION_TAKEN_BY_REF = 2L;

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORY = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDLE_TIME = 1;
    private static final Integer UPDATED_IDLE_TIME = 2;

    private static final String DEFAULT_IDLE_TIME_MEASURE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_IDLE_TIME_MEASURE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CLOSURE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CLOSURE_STATUS = "BBBBBBBBBB";

    @Autowired
    private TicketActionRepository ticketActionRepository;

    @Autowired
    private TicketActionMapper ticketActionMapper;

    @Autowired
    private TicketActionService ticketActionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTicketActionMockMvc;

    private TicketAction ticketAction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketAction createEntity(EntityManager em) {
        TicketAction ticketAction = new TicketAction()
            .actionName(DEFAULT_ACTION_NAME)
            .status(DEFAULT_STATUS)
            .actions(DEFAULT_ACTIONS)
            .actionTakenByRef(DEFAULT_ACTION_TAKEN_BY_REF)
            .category(DEFAULT_CATEGORY)
            .subcategory(DEFAULT_SUBCATEGORY)
            .idleTime(DEFAULT_IDLE_TIME)
            .idleTimeMeasureType(DEFAULT_IDLE_TIME_MEASURE_TYPE)
            .comment(DEFAULT_COMMENT)
            .closureStatus(DEFAULT_CLOSURE_STATUS);
        return ticketAction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketAction createUpdatedEntity(EntityManager em) {
        TicketAction ticketAction = new TicketAction()
            .actionName(UPDATED_ACTION_NAME)
            .status(UPDATED_STATUS)
            .actions(UPDATED_ACTIONS)
            .actionTakenByRef(UPDATED_ACTION_TAKEN_BY_REF)
            .category(UPDATED_CATEGORY)
            .subcategory(UPDATED_SUBCATEGORY)
            .idleTime(UPDATED_IDLE_TIME)
            .idleTimeMeasureType(UPDATED_IDLE_TIME_MEASURE_TYPE)
            .comment(UPDATED_COMMENT)
            .closureStatus(UPDATED_CLOSURE_STATUS);
        return ticketAction;
    }

    @BeforeEach
    public void initTest() {
        ticketAction = createEntity(em);
    }

    @Test
    @Transactional
    public void createTicketAction() throws Exception {
        int databaseSizeBeforeCreate = ticketActionRepository.findAll().size();
        // Create the TicketAction
        TicketActionDTO ticketActionDTO = ticketActionMapper.toDto(ticketAction);
        restTicketActionMockMvc.perform(post("/api/ticket-actions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketActionDTO)))
            .andExpect(status().isCreated());

        // Validate the TicketAction in the database
        List<TicketAction> ticketActionList = ticketActionRepository.findAll();
        assertThat(ticketActionList).hasSize(databaseSizeBeforeCreate + 1);
        TicketAction testTicketAction = ticketActionList.get(ticketActionList.size() - 1);
        assertThat(testTicketAction.getActionName()).isEqualTo(DEFAULT_ACTION_NAME);
        assertThat(testTicketAction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTicketAction.getActions()).isEqualTo(DEFAULT_ACTIONS);
        assertThat(testTicketAction.getActionTakenByRef()).isEqualTo(DEFAULT_ACTION_TAKEN_BY_REF);
        assertThat(testTicketAction.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTicketAction.getSubcategory()).isEqualTo(DEFAULT_SUBCATEGORY);
        assertThat(testTicketAction.getIdleTime()).isEqualTo(DEFAULT_IDLE_TIME);
        assertThat(testTicketAction.getIdleTimeMeasureType()).isEqualTo(DEFAULT_IDLE_TIME_MEASURE_TYPE);
        assertThat(testTicketAction.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testTicketAction.getClosureStatus()).isEqualTo(DEFAULT_CLOSURE_STATUS);
    }

    @Test
    @Transactional
    public void createTicketActionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ticketActionRepository.findAll().size();

        // Create the TicketAction with an existing ID
        ticketAction.setId(1L);
        TicketActionDTO ticketActionDTO = ticketActionMapper.toDto(ticketAction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketActionMockMvc.perform(post("/api/ticket-actions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TicketAction in the database
        List<TicketAction> ticketActionList = ticketActionRepository.findAll();
        assertThat(ticketActionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTicketActions() throws Exception {
        // Initialize the database
        ticketActionRepository.saveAndFlush(ticketAction);

        // Get all the ticketActionList
        restTicketActionMockMvc.perform(get("/api/ticket-actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticketAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionName").value(hasItem(DEFAULT_ACTION_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].actions").value(hasItem(DEFAULT_ACTIONS)))
            .andExpect(jsonPath("$.[*].actionTakenByRef").value(hasItem(DEFAULT_ACTION_TAKEN_BY_REF.intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].subcategory").value(hasItem(DEFAULT_SUBCATEGORY)))
            .andExpect(jsonPath("$.[*].idleTime").value(hasItem(DEFAULT_IDLE_TIME)))
            .andExpect(jsonPath("$.[*].idleTimeMeasureType").value(hasItem(DEFAULT_IDLE_TIME_MEASURE_TYPE)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].closureStatus").value(hasItem(DEFAULT_CLOSURE_STATUS)));
    }
    
    @Test
    @Transactional
    public void getTicketAction() throws Exception {
        // Initialize the database
        ticketActionRepository.saveAndFlush(ticketAction);

        // Get the ticketAction
        restTicketActionMockMvc.perform(get("/api/ticket-actions/{id}", ticketAction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticketAction.getId().intValue()))
            .andExpect(jsonPath("$.actionName").value(DEFAULT_ACTION_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.actions").value(DEFAULT_ACTIONS))
            .andExpect(jsonPath("$.actionTakenByRef").value(DEFAULT_ACTION_TAKEN_BY_REF.intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.subcategory").value(DEFAULT_SUBCATEGORY))
            .andExpect(jsonPath("$.idleTime").value(DEFAULT_IDLE_TIME))
            .andExpect(jsonPath("$.idleTimeMeasureType").value(DEFAULT_IDLE_TIME_MEASURE_TYPE))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.closureStatus").value(DEFAULT_CLOSURE_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingTicketAction() throws Exception {
        // Get the ticketAction
        restTicketActionMockMvc.perform(get("/api/ticket-actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTicketAction() throws Exception {
        // Initialize the database
        ticketActionRepository.saveAndFlush(ticketAction);

        int databaseSizeBeforeUpdate = ticketActionRepository.findAll().size();

        // Update the ticketAction
        TicketAction updatedTicketAction = ticketActionRepository.findById(ticketAction.getId()).get();
        // Disconnect from session so that the updates on updatedTicketAction are not directly saved in db
        em.detach(updatedTicketAction);
        updatedTicketAction
            .actionName(UPDATED_ACTION_NAME)
            .status(UPDATED_STATUS)
            .actions(UPDATED_ACTIONS)
            .actionTakenByRef(UPDATED_ACTION_TAKEN_BY_REF)
            .category(UPDATED_CATEGORY)
            .subcategory(UPDATED_SUBCATEGORY)
            .idleTime(UPDATED_IDLE_TIME)
            .idleTimeMeasureType(UPDATED_IDLE_TIME_MEASURE_TYPE)
            .comment(UPDATED_COMMENT)
            .closureStatus(UPDATED_CLOSURE_STATUS);
        TicketActionDTO ticketActionDTO = ticketActionMapper.toDto(updatedTicketAction);

        restTicketActionMockMvc.perform(put("/api/ticket-actions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketActionDTO)))
            .andExpect(status().isOk());

        // Validate the TicketAction in the database
        List<TicketAction> ticketActionList = ticketActionRepository.findAll();
        assertThat(ticketActionList).hasSize(databaseSizeBeforeUpdate);
        TicketAction testTicketAction = ticketActionList.get(ticketActionList.size() - 1);
        assertThat(testTicketAction.getActionName()).isEqualTo(UPDATED_ACTION_NAME);
        assertThat(testTicketAction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTicketAction.getActions()).isEqualTo(UPDATED_ACTIONS);
        assertThat(testTicketAction.getActionTakenByRef()).isEqualTo(UPDATED_ACTION_TAKEN_BY_REF);
        assertThat(testTicketAction.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTicketAction.getSubcategory()).isEqualTo(UPDATED_SUBCATEGORY);
        assertThat(testTicketAction.getIdleTime()).isEqualTo(UPDATED_IDLE_TIME);
        assertThat(testTicketAction.getIdleTimeMeasureType()).isEqualTo(UPDATED_IDLE_TIME_MEASURE_TYPE);
        assertThat(testTicketAction.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testTicketAction.getClosureStatus()).isEqualTo(UPDATED_CLOSURE_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTicketAction() throws Exception {
        int databaseSizeBeforeUpdate = ticketActionRepository.findAll().size();

        // Create the TicketAction
        TicketActionDTO ticketActionDTO = ticketActionMapper.toDto(ticketAction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketActionMockMvc.perform(put("/api/ticket-actions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TicketAction in the database
        List<TicketAction> ticketActionList = ticketActionRepository.findAll();
        assertThat(ticketActionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTicketAction() throws Exception {
        // Initialize the database
        ticketActionRepository.saveAndFlush(ticketAction);

        int databaseSizeBeforeDelete = ticketActionRepository.findAll().size();

        // Delete the ticketAction
        restTicketActionMockMvc.perform(delete("/api/ticket-actions/{id}", ticketAction.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TicketAction> ticketActionList = ticketActionRepository.findAll();
        assertThat(ticketActionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

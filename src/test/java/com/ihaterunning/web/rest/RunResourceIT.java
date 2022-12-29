package com.ihaterunning.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ihaterunning.IntegrationTest;
import com.ihaterunning.domain.Run;
import com.ihaterunning.repository.RunRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RunResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class RunResourceIT {

    private static final String DEFAULT_RUN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RUN_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RUN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RUN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_DISTANCE = 1D;
    private static final Double UPDATED_DISTANCE = 2D;

    private static final Double DEFAULT_TIME = 1D;
    private static final Double UPDATED_TIME = 2D;

    private static final Double DEFAULT_PACE = 1D;
    private static final Double UPDATED_PACE = 2D;

    private static final String ENTITY_API_URL = "/api/runs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RunRepository runRepository;

    @Mock
    private RunRepository runRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRunMockMvc;

    private Run run;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Run createEntity(EntityManager em) {
        Run run = new Run()
            .runName(DEFAULT_RUN_NAME)
            .runDate(DEFAULT_RUN_DATE)
            .distance(DEFAULT_DISTANCE)
            .time(DEFAULT_TIME)
            .pace(DEFAULT_PACE);
        return run;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Run createUpdatedEntity(EntityManager em) {
        Run run = new Run()
            .runName(UPDATED_RUN_NAME)
            .runDate(UPDATED_RUN_DATE)
            .distance(UPDATED_DISTANCE)
            .time(UPDATED_TIME)
            .pace(UPDATED_PACE);
        return run;
    }

    @BeforeEach
    public void initTest() {
        run = createEntity(em);
    }

    @Test
    @Transactional
    void createRun() throws Exception {
        int databaseSizeBeforeCreate = runRepository.findAll().size();
        // Create the Run
        restRunMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isCreated());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeCreate + 1);
        Run testRun = runList.get(runList.size() - 1);
        assertThat(testRun.getRunName()).isEqualTo(DEFAULT_RUN_NAME);
        assertThat(testRun.getRunDate()).isEqualTo(DEFAULT_RUN_DATE);
        assertThat(testRun.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testRun.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testRun.getPace()).isEqualTo(DEFAULT_PACE);
    }

    @Test
    @Transactional
    void createRunWithExistingId() throws Exception {
        // Create the Run with an existing ID
        run.setId(1L);

        int databaseSizeBeforeCreate = runRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRunMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isBadRequest());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRunNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = runRepository.findAll().size();
        // set the field null
        run.setRunName(null);

        // Create the Run, which fails.

        restRunMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isBadRequest());

        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRunDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = runRepository.findAll().size();
        // set the field null
        run.setRunDate(null);

        // Create the Run, which fails.

        restRunMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isBadRequest());

        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDistanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = runRepository.findAll().size();
        // set the field null
        run.setDistance(null);

        // Create the Run, which fails.

        restRunMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isBadRequest());

        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = runRepository.findAll().size();
        // set the field null
        run.setTime(null);

        // Create the Run, which fails.

        restRunMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isBadRequest());

        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRuns() throws Exception {
        // Initialize the database
        runRepository.saveAndFlush(run);

        // Get all the runList
        restRunMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(run.getId().intValue())))
            .andExpect(jsonPath("$.[*].runName").value(hasItem(DEFAULT_RUN_NAME)))
            .andExpect(jsonPath("$.[*].runDate").value(hasItem(DEFAULT_RUN_DATE.toString())))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].pace").value(hasItem(DEFAULT_PACE.doubleValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRunsWithEagerRelationshipsIsEnabled() throws Exception {
        when(runRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRunMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(runRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRunsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(runRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRunMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(runRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getRun() throws Exception {
        // Initialize the database
        runRepository.saveAndFlush(run);

        // Get the run
        restRunMockMvc
            .perform(get(ENTITY_API_URL_ID, run.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(run.getId().intValue()))
            .andExpect(jsonPath("$.runName").value(DEFAULT_RUN_NAME))
            .andExpect(jsonPath("$.runDate").value(DEFAULT_RUN_DATE.toString()))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.doubleValue()))
            .andExpect(jsonPath("$.pace").value(DEFAULT_PACE.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingRun() throws Exception {
        // Get the run
        restRunMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRun() throws Exception {
        // Initialize the database
        runRepository.saveAndFlush(run);

        int databaseSizeBeforeUpdate = runRepository.findAll().size();

        // Update the run
        Run updatedRun = runRepository.findById(run.getId()).get();
        // Disconnect from session so that the updates on updatedRun are not directly saved in db
        em.detach(updatedRun);
        updatedRun.runName(UPDATED_RUN_NAME).runDate(UPDATED_RUN_DATE).distance(UPDATED_DISTANCE).time(UPDATED_TIME).pace(UPDATED_PACE);

        restRunMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRun.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRun))
            )
            .andExpect(status().isOk());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
        Run testRun = runList.get(runList.size() - 1);
        assertThat(testRun.getRunName()).isEqualTo(UPDATED_RUN_NAME);
        assertThat(testRun.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testRun.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testRun.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testRun.getPace()).isEqualTo(UPDATED_PACE);
    }

    @Test
    @Transactional
    void putNonExistingRun() throws Exception {
        int databaseSizeBeforeUpdate = runRepository.findAll().size();
        run.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRunMockMvc
            .perform(
                put(ENTITY_API_URL_ID, run.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run))
            )
            .andExpect(status().isBadRequest());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRun() throws Exception {
        int databaseSizeBeforeUpdate = runRepository.findAll().size();
        run.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRunMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(run))
            )
            .andExpect(status().isBadRequest());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRun() throws Exception {
        int databaseSizeBeforeUpdate = runRepository.findAll().size();
        run.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRunMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRunWithPatch() throws Exception {
        // Initialize the database
        runRepository.saveAndFlush(run);

        int databaseSizeBeforeUpdate = runRepository.findAll().size();

        // Update the run using partial update
        Run partialUpdatedRun = new Run();
        partialUpdatedRun.setId(run.getId());

        partialUpdatedRun.runDate(UPDATED_RUN_DATE);

        restRunMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRun.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRun))
            )
            .andExpect(status().isOk());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
        Run testRun = runList.get(runList.size() - 1);
        assertThat(testRun.getRunName()).isEqualTo(DEFAULT_RUN_NAME);
        assertThat(testRun.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testRun.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testRun.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testRun.getPace()).isEqualTo(DEFAULT_PACE);
    }

    @Test
    @Transactional
    void fullUpdateRunWithPatch() throws Exception {
        // Initialize the database
        runRepository.saveAndFlush(run);

        int databaseSizeBeforeUpdate = runRepository.findAll().size();

        // Update the run using partial update
        Run partialUpdatedRun = new Run();
        partialUpdatedRun.setId(run.getId());

        partialUpdatedRun
            .runName(UPDATED_RUN_NAME)
            .runDate(UPDATED_RUN_DATE)
            .distance(UPDATED_DISTANCE)
            .time(UPDATED_TIME)
            .pace(UPDATED_PACE);

        restRunMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRun.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRun))
            )
            .andExpect(status().isOk());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
        Run testRun = runList.get(runList.size() - 1);
        assertThat(testRun.getRunName()).isEqualTo(UPDATED_RUN_NAME);
        assertThat(testRun.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testRun.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testRun.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testRun.getPace()).isEqualTo(UPDATED_PACE);
    }

    @Test
    @Transactional
    void patchNonExistingRun() throws Exception {
        int databaseSizeBeforeUpdate = runRepository.findAll().size();
        run.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRunMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, run.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(run))
            )
            .andExpect(status().isBadRequest());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRun() throws Exception {
        int databaseSizeBeforeUpdate = runRepository.findAll().size();
        run.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRunMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(run))
            )
            .andExpect(status().isBadRequest());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRun() throws Exception {
        int databaseSizeBeforeUpdate = runRepository.findAll().size();
        run.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRunMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(run)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Run in the database
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRun() throws Exception {
        // Initialize the database
        runRepository.saveAndFlush(run);

        int databaseSizeBeforeDelete = runRepository.findAll().size();

        // Delete the run
        restRunMockMvc.perform(delete(ENTITY_API_URL_ID, run.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Run> runList = runRepository.findAll();
        assertThat(runList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

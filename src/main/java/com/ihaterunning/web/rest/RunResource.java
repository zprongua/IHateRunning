package com.ihaterunning.web.rest;

import com.ihaterunning.domain.Run;
import com.ihaterunning.repository.RunRepository;
import com.ihaterunning.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ihaterunning.domain.Run}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RunResource {

    private final Logger log = LoggerFactory.getLogger(RunResource.class);

    private static final String ENTITY_NAME = "run";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RunRepository runRepository;

    public RunResource(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    /**
     * {@code POST  /runs} : Create a new run.
     *
     * @param run the run to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new run, or with status {@code 400 (Bad Request)} if the run has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/runs")
    public ResponseEntity<Run> createRun(@Valid @RequestBody Run run) throws URISyntaxException {
        log.debug("REST request to save Run : {}", run);
        if (run.getId() != null) {
            throw new BadRequestAlertException("A new run cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Run result = runRepository.save(run);
        return ResponseEntity
            .created(new URI("/api/runs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /runs/:id} : Updates an existing run.
     *
     * @param id the id of the run to save.
     * @param run the run to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated run,
     * or with status {@code 400 (Bad Request)} if the run is not valid,
     * or with status {@code 500 (Internal Server Error)} if the run couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/runs/{id}")
    public ResponseEntity<Run> updateRun(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Run run)
        throws URISyntaxException {
        log.debug("REST request to update Run : {}, {}", id, run);
        if (run.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, run.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!runRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Run result = runRepository.save(run);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, run.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /runs/:id} : Partial updates given fields of an existing run, field will ignore if it is null
     *
     * @param id the id of the run to save.
     * @param run the run to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated run,
     * or with status {@code 400 (Bad Request)} if the run is not valid,
     * or with status {@code 404 (Not Found)} if the run is not found,
     * or with status {@code 500 (Internal Server Error)} if the run couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/runs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Run> partialUpdateRun(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody Run run)
        throws URISyntaxException {
        log.debug("REST request to partial update Run partially : {}, {}", id, run);
        if (run.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, run.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!runRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Run> result = runRepository
            .findById(run.getId())
            .map(existingRun -> {
                if (run.getRunName() != null) {
                    existingRun.setRunName(run.getRunName());
                }
                if (run.getRunDate() != null) {
                    existingRun.setRunDate(run.getRunDate());
                }
                if (run.getDistance() != null) {
                    existingRun.setDistance(run.getDistance());
                }
                if (run.getTime() != null) {
                    existingRun.setTime(run.getTime());
                }
                if (run.getPace() != null) {
                    existingRun.setPace(run.getPace());
                }

                return existingRun;
            })
            .map(runRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, run.getId().toString())
        );
    }

    /**
     * {@code GET  /runs} : get all the runs.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of runs in body.
     */
    @GetMapping("/runs")
    public List<Run> getAllRuns(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Runs");
        if (eagerload) {
            return runRepository.findAllWithEagerRelationships();
        } else {
            return runRepository.findByUserIsCurrentUser();
        }
    }

    /**
     * {@code GET  /runs/:id} : get the "id" run.
     *
     * @param id the id of the run to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the run, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/runs/{id}")
    public ResponseEntity<Run> getRun(@PathVariable Long id) {
        log.debug("REST request to get Run : {}", id);
        Optional<Run> run = runRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(run);
    }

    /**
     * {@code DELETE  /runs/:id} : delete the "id" run.
     *
     * @param id the id of the run to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/runs/{id}")
    public ResponseEntity<Void> deleteRun(@PathVariable Long id) {
        log.debug("REST request to delete Run : {}", id);
        runRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

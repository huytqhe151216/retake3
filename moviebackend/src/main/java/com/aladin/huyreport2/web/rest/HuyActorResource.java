package com.aladin.huyreport2.web.rest;

import com.aladin.huyreport2.domain.HuyActor;
import com.aladin.huyreport2.service.HuyActorService;
import com.aladin.huyreport2.web.rest.errors.BadRequestAlertException;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.aladin.huyreport2.domain.HuyActor}.
 */
@RestController
@RequestMapping("/api")
public class HuyActorResource {

    private final Logger log = LoggerFactory.getLogger(HuyActorResource.class);

    private static final String ENTITY_NAME = "moviebackendHuyActor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HuyActorService huyActorService;

    public HuyActorResource(HuyActorService huyActorService) {
        this.huyActorService = huyActorService;
    }

    /**
     * {@code POST  /huy-actors} : Create a new huyActor.
     *
     * @param huyActor the huyActor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new huyActor, or with status {@code 400 (Bad Request)} if the huyActor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/huy-actors")
    public ResponseEntity<HuyActor> createHuyActor(@Valid @RequestBody HuyActor huyActor) throws URISyntaxException {
        log.debug("REST request to save HuyActor : {}", huyActor);
        if (huyActor.getId() != null) {
            throw new BadRequestAlertException("A new huyActor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HuyActor result = huyActorService.save(huyActor);
        return ResponseEntity.created(new URI("/api/huy-actors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /huy-actors} : Updates an existing huyActor.
     *
     * @param huyActor the huyActor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated huyActor,
     * or with status {@code 400 (Bad Request)} if the huyActor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the huyActor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/huy-actors")
    public ResponseEntity<HuyActor> updateHuyActor(@Valid @RequestBody HuyActor huyActor) throws URISyntaxException {
        log.debug("REST request to update HuyActor : {}", huyActor);
        if (huyActor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HuyActor result = huyActorService.save(huyActor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, huyActor.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /huy-actors} : get all the huyActors.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of huyActors in body.
     */
    @GetMapping("/huy-actors")
    public ResponseEntity<List<HuyActor>> getAllHuyActors(Pageable pageable) {
        log.debug("REST request to get a page of HuyActors");
        Page<HuyActor> page = huyActorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /huy-actors/:id} : get the "id" huyActor.
     *
     * @param id the id of the huyActor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the huyActor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/huy-actors/{id}")
    public ResponseEntity<HuyActor> getHuyActor(@PathVariable Long id) {
        log.debug("REST request to get HuyActor : {}", id);
        Optional<HuyActor> huyActor = huyActorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(huyActor);
    }

    /**
     * {@code DELETE  /huy-actors/:id} : delete the "id" huyActor.
     *
     * @param id the id of the huyActor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/huy-actors/{id}")
    public ResponseEntity<Void> deleteHuyActor(@PathVariable Long id) {
        log.debug("REST request to delete HuyActor : {}", id);
        huyActorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/huy-actors?query=:query} : search for the huyActor corresponding
     * to the query.
     *
     * @param query the query of the huyActor search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/huy-actors")
    public ResponseEntity<List<HuyActor>> searchHuyActors(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of HuyActors for query {}", query);
        Page<HuyActor> page = huyActorService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

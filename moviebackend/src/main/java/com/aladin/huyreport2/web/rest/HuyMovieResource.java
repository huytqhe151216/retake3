package com.aladin.huyreport2.web.rest;

import com.aladin.huyreport2.domain.HuyMovie;
import com.aladin.huyreport2.service.HuyMovieService;
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
 * REST controller for managing {@link com.aladin.huyreport2.domain.HuyMovie}.
 */
@RestController
@RequestMapping("/api")
public class HuyMovieResource {

    private final Logger log = LoggerFactory.getLogger(HuyMovieResource.class);

    private static final String ENTITY_NAME = "moviebackendHuyMovie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HuyMovieService huyMovieService;

    public HuyMovieResource(HuyMovieService huyMovieService) {
        this.huyMovieService = huyMovieService;
    }

    /**
     * {@code POST  /huy-movies} : Create a new huyMovie.
     *
     * @param huyMovie the huyMovie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new huyMovie, or with status {@code 400 (Bad Request)} if the huyMovie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/huy-movies")
    public ResponseEntity<HuyMovie> createHuyMovie(@Valid @RequestBody HuyMovie huyMovie) throws URISyntaxException {
        log.debug("REST request to save HuyMovie : {}", huyMovie);
        if (huyMovie.getId() != null) {
            throw new BadRequestAlertException("A new huyMovie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HuyMovie result = huyMovieService.save(huyMovie);
        return ResponseEntity.created(new URI("/api/huy-movies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /huy-movies} : Updates an existing huyMovie.
     *
     * @param huyMovie the huyMovie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated huyMovie,
     * or with status {@code 400 (Bad Request)} if the huyMovie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the huyMovie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/huy-movies")
    public ResponseEntity<HuyMovie> updateHuyMovie(@Valid @RequestBody HuyMovie huyMovie) throws URISyntaxException {
        log.debug("REST request to update HuyMovie : {}", huyMovie);
        if (huyMovie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HuyMovie result = huyMovieService.save(huyMovie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, huyMovie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /huy-movies} : get all the huyMovies.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of huyMovies in body.
     */
    @GetMapping("/huy-movies")
    public ResponseEntity<List<HuyMovie>> getAllHuyMovies(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of HuyMovies");
        Page<HuyMovie> page;
        if (eagerload) {
            page = huyMovieService.findAllWithEagerRelationships(pageable);
        } else {
            page = huyMovieService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /huy-movies/:id} : get the "id" huyMovie.
     *
     * @param id the id of the huyMovie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the huyMovie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/huy-movies/{id}")
    public ResponseEntity<HuyMovie> getHuyMovie(@PathVariable Long id) {
        log.debug("REST request to get HuyMovie : {}", id);
        Optional<HuyMovie> huyMovie = huyMovieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(huyMovie);
    }

    /**
     * {@code DELETE  /huy-movies/:id} : delete the "id" huyMovie.
     *
     * @param id the id of the huyMovie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/huy-movies/{id}")
    public ResponseEntity<Void> deleteHuyMovie(@PathVariable Long id) {
        log.debug("REST request to delete HuyMovie : {}", id);
        huyMovieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/huy-movies?query=:query} : search for the huyMovie corresponding
     * to the query.
     *
     * @param query the query of the huyMovie search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/huy-movies")
    public ResponseEntity<List<HuyMovie>> searchHuyMovies(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of HuyMovies for query {}", query);
        Page<HuyMovie> page = huyMovieService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

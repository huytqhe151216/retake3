package com.aladin.huyreport2.web.rest;

import com.aladin.huyreport2.domain.HuyGenre;
import com.aladin.huyreport2.service.HuyGenreService;
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
 * REST controller for managing {@link com.aladin.huyreport2.domain.HuyGenre}.
 */
@RestController
@RequestMapping("/api")
public class HuyGenreResource {

    private final Logger log = LoggerFactory.getLogger(HuyGenreResource.class);

    private static final String ENTITY_NAME = "moviebackendHuyGenre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HuyGenreService huyGenreService;

    public HuyGenreResource(HuyGenreService huyGenreService) {
        this.huyGenreService = huyGenreService;
    }

    /**
     * {@code POST  /huy-genres} : Create a new huyGenre.
     *
     * @param huyGenre the huyGenre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new huyGenre, or with status {@code 400 (Bad Request)} if the huyGenre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/huy-genres")
    public ResponseEntity<HuyGenre> createHuyGenre(@Valid @RequestBody HuyGenre huyGenre) throws URISyntaxException {
        log.debug("REST request to save HuyGenre : {}", huyGenre);
        if (huyGenre.getId() != null) {
            throw new BadRequestAlertException("A new huyGenre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HuyGenre result = huyGenreService.save(huyGenre);
        return ResponseEntity.created(new URI("/api/huy-genres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /huy-genres} : Updates an existing huyGenre.
     *
     * @param huyGenre the huyGenre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated huyGenre,
     * or with status {@code 400 (Bad Request)} if the huyGenre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the huyGenre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/huy-genres")
    public ResponseEntity<HuyGenre> updateHuyGenre(@Valid @RequestBody HuyGenre huyGenre) throws URISyntaxException {
        log.debug("REST request to update HuyGenre : {}", huyGenre);
        if (huyGenre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HuyGenre result = huyGenreService.save(huyGenre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, huyGenre.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /huy-genres} : get all the huyGenres.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of huyGenres in body.
     */
    @GetMapping("/huy-genres")
    public ResponseEntity<List<HuyGenre>> getAllHuyGenres(Pageable pageable) {
        log.debug("REST request to get a page of HuyGenres");
        Page<HuyGenre> page = huyGenreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /huy-genres/:id} : get the "id" huyGenre.
     *
     * @param id the id of the huyGenre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the huyGenre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/huy-genres/{id}")
    public ResponseEntity<HuyGenre> getHuyGenre(@PathVariable Long id) {
        log.debug("REST request to get HuyGenre : {}", id);
        Optional<HuyGenre> huyGenre = huyGenreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(huyGenre);
    }

    /**
     * {@code DELETE  /huy-genres/:id} : delete the "id" huyGenre.
     *
     * @param id the id of the huyGenre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/huy-genres/{id}")
    public ResponseEntity<Void> deleteHuyGenre(@PathVariable Long id) {
        log.debug("REST request to delete HuyGenre : {}", id);
        huyGenreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/huy-genres?query=:query} : search for the huyGenre corresponding
     * to the query.
     *
     * @param query the query of the huyGenre search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/huy-genres")
    public ResponseEntity<List<HuyGenre>> searchHuyGenres(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of HuyGenres for query {}", query);
        Page<HuyGenre> page = huyGenreService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

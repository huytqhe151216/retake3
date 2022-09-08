package com.aladin.huyreport2.web.rest;

import com.aladin.huyreport2.domain.HuyRate;
import com.aladin.huyreport2.domain.User;
import com.aladin.huyreport2.repository.HuyRateRepository;
import com.aladin.huyreport2.repository.search.HuyRateSearchRepository;
import com.aladin.huyreport2.service.HuyRateService;
import com.aladin.huyreport2.service.UserService;
import com.aladin.huyreport2.service.impl.HuyRateServiceImpl;
import com.aladin.huyreport2.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.aladin.huyreport2.domain.HuyRate}.
 */
@RestController
@RequestMapping("/api")
public class HuyRateResource {

    private final Logger log = LoggerFactory.getLogger(HuyRateResource.class);

    private static final String ENTITY_NAME = "moviebackendHuyRate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    @Autowired
    private HuyRateServiceImpl huyRateServiceImpl;
    @Autowired
    private UserService userService;
    private final HuyRateService huyRateService;

    public HuyRateResource(HuyRateService huyRateService) {
        this.huyRateService = huyRateService;
    }

    /**
     * {@code POST  /huy-rates} : Create a new huyRate.
     *
     * @param huyRate the huyRate to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new huyRate, or with status {@code 400 (Bad Request)} if the huyRate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/huy-rates")
    public ResponseEntity<HuyRate> createHuyRate(@Valid @RequestBody HuyRate huyRate) throws URISyntaxException {
        log.debug("REST request to save HuyRate : {}", huyRate);
        if (huyRate.getId() != null) {
            throw new BadRequestAlertException("A new huyRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        User user =userService.getUserWithAuthorities().get();
        huyRate.setUser(user);
        huyRate.setDateCreate(Instant.now());
        HuyRate result = huyRateServiceImpl.save(huyRate);
        return ResponseEntity.created(new URI("/api/huy-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /huy-rates} : Updates an existing huyRate.
     *
     * @param huyRate the huyRate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated huyRate,
     * or with status {@code 400 (Bad Request)} if the huyRate is not valid,
     * or with status {@code 500 (Internal Server Error)} if the huyRate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/huy-rates")
    public ResponseEntity<HuyRate> updateHuyRate(@Valid @RequestBody HuyRate huyRate) throws URISyntaxException {
        log.debug("REST request to update HuyRate : {}", huyRate);
        if (huyRate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Long id1 = userService.getUserWithAuthorities().get().getId();
        Long id2 = huyRateServiceImpl.findOne(huyRate.getId()).get().getUser().getId();
        if (id1!=id2){
            log.info("Can't edit other people's rate");
            return ResponseEntity.badRequest().body(null);
        }
        huyRate.setDateCreate(Instant.now());
        huyRate.setUser(userService.getUserWithAuthorities().get());
        HuyRate result = huyRateServiceImpl.save(huyRate);
        log.info("edit successfully");
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, huyRate.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /huy-rates} : get all the huyRates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of huyRates in body.
     */
    @GetMapping("/huy-rates")
    public ResponseEntity<List<HuyRate>> getAllHuyRates(Pageable pageable) {
        log.debug("REST request to get a page of HuyRates");
        Page<HuyRate> page = huyRateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /huy-rates/:id} : get the "id" huyRate.
     *
     * @param id the id of the huyRate to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the huyRate, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/huy-rates/{id}")
    public ResponseEntity<HuyRate> getHuyRate(@PathVariable Long id) {
        log.debug("REST request to get HuyRate : {}", id);
        Optional<HuyRate> huyRate = huyRateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(huyRate);
    }

    /**
     * {@code DELETE  /huy-rates/:id} : delete the "id" huyRate.
     *
     * @param id the id of the huyRate to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/huy-rates/{id}")
    public ResponseEntity<Void> deleteHuyRate(@PathVariable Long id) {
        log.debug("REST request to delete HuyRate : {}", id);
        User user = userService.getUserWithAuthorities().get();
        Long id2 = huyRateServiceImpl.findOne(id).get().getUser().getId();
        if (user.getId()!=id2 && user.getAuthorities().stream().noneMatch(x->x.getName().equals("ADMIN_ROLE"))){
            throw new AuthorizationServiceException("You are not authorized to delete this rate");
        }
        huyRateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/huy-rates?query=:query} : search for the huyRate corresponding
     * to the query.
     *
     * @param query the query of the huyRate search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/huy-rates")
    public ResponseEntity<List<HuyRate>> searchHuyRates(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of HuyRates for query {}", query);
        Page<HuyRate> page = huyRateService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

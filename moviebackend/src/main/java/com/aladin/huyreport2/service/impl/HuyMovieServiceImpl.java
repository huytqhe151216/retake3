package com.aladin.huyreport2.service.impl;

import com.aladin.huyreport2.service.HuyMovieService;
import com.aladin.huyreport2.domain.HuyMovie;
import com.aladin.huyreport2.repository.HuyMovieRepository;
import com.aladin.huyreport2.repository.search.HuyMovieSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link HuyMovie}.
 */
@Service
@Transactional
public class HuyMovieServiceImpl implements HuyMovieService {

    private final Logger log = LoggerFactory.getLogger(HuyMovieServiceImpl.class);

    private final HuyMovieRepository huyMovieRepository;

    private final HuyMovieSearchRepository huyMovieSearchRepository;

    public HuyMovieServiceImpl(HuyMovieRepository huyMovieRepository, HuyMovieSearchRepository huyMovieSearchRepository) {
        this.huyMovieRepository = huyMovieRepository;
        this.huyMovieSearchRepository = huyMovieSearchRepository;
    }

    /**
     * Save a huyMovie.
     *
     * @param huyMovie the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HuyMovie save(HuyMovie huyMovie) {
        log.debug("Request to save HuyMovie : {}", huyMovie);
        HuyMovie result = huyMovieRepository.save(huyMovie);
        huyMovieSearchRepository.save(result);
        return result;
    }
    public HuyMovie findMovieByRateId(long rateId){
        return huyMovieRepository.findHuyMovieByRate(rateId);
    }
    /**
     * Get all the huyMovies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyMovie> findAll(Pageable pageable) {
        log.debug("Request to get all HuyMovies");
        return huyMovieRepository.findAll(pageable);
    }

    /**
     * Get all the huyMovies with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<HuyMovie> findAllWithEagerRelationships(Pageable pageable) {
        return huyMovieRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one huyMovie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HuyMovie> findOne(Long id) {
        log.debug("Request to get HuyMovie : {}", id);
        return huyMovieRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the huyMovie by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HuyMovie : {}", id);
        huyMovieRepository.deleteById(id);
        huyMovieSearchRepository.deleteById(id);
    }

    /**
     * Search for the huyMovie corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyMovie> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of HuyMovies for query {}", query);
        return huyMovieSearchRepository.search(queryStringQuery(query), pageable);    }
}

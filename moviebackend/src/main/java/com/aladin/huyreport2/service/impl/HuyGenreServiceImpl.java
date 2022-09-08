package com.aladin.huyreport2.service.impl;

import com.aladin.huyreport2.service.HuyGenreService;
import com.aladin.huyreport2.domain.HuyGenre;
import com.aladin.huyreport2.repository.HuyGenreRepository;
import com.aladin.huyreport2.repository.search.HuyGenreSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link HuyGenre}.
 */
@Service
@Transactional
public class HuyGenreServiceImpl implements HuyGenreService {

    private final Logger log = LoggerFactory.getLogger(HuyGenreServiceImpl.class);

    private final HuyGenreRepository huyGenreRepository;

    private final HuyGenreSearchRepository huyGenreSearchRepository;

    public HuyGenreServiceImpl(HuyGenreRepository huyGenreRepository, HuyGenreSearchRepository huyGenreSearchRepository) {
        this.huyGenreRepository = huyGenreRepository;
        this.huyGenreSearchRepository = huyGenreSearchRepository;
    }

    /**
     * Save a huyGenre.
     *
     * @param huyGenre the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HuyGenre save(HuyGenre huyGenre) {
        log.debug("Request to save HuyGenre : {}", huyGenre);
        HuyGenre result = huyGenreRepository.save(huyGenre);
        huyGenreSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the huyGenres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyGenre> findAll(Pageable pageable) {
        log.debug("Request to get all HuyGenres");
        return huyGenreRepository.findAll(pageable);
    }

    /**
     * Get one huyGenre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HuyGenre> findOne(Long id) {
        log.debug("Request to get HuyGenre : {}", id);
        return huyGenreRepository.findById(id);
    }

    /**
     * Delete the huyGenre by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HuyGenre : {}", id);
        huyGenreRepository.deleteById(id);
        huyGenreSearchRepository.deleteById(id);
    }

    /**
     * Search for the huyGenre corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyGenre> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of HuyGenres for query {}", query);
        return huyGenreSearchRepository.search(queryStringQuery(query), pageable);    }
}

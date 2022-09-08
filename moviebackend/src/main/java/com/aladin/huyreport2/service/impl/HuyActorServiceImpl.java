package com.aladin.huyreport2.service.impl;

import com.aladin.huyreport2.service.HuyActorService;
import com.aladin.huyreport2.domain.HuyActor;
import com.aladin.huyreport2.repository.HuyActorRepository;
import com.aladin.huyreport2.repository.search.HuyActorSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link HuyActor}.
 */
@Service
@Transactional
public class HuyActorServiceImpl implements HuyActorService {

    private final Logger log = LoggerFactory.getLogger(HuyActorServiceImpl.class);

    private final HuyActorRepository huyActorRepository;

    private final HuyActorSearchRepository huyActorSearchRepository;

    public HuyActorServiceImpl(HuyActorRepository huyActorRepository, HuyActorSearchRepository huyActorSearchRepository) {
        this.huyActorRepository = huyActorRepository;
        this.huyActorSearchRepository = huyActorSearchRepository;
    }

    /**
     * Save a huyActor.
     *
     * @param huyActor the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HuyActor save(HuyActor huyActor) {
        log.debug("Request to save HuyActor : {}", huyActor);
        HuyActor result = huyActorRepository.save(huyActor);
        huyActorSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the huyActors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyActor> findAll(Pageable pageable) {
        log.debug("Request to get all HuyActors");
        return huyActorRepository.findAll(pageable);
    }

    /**
     * Get one huyActor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HuyActor> findOne(Long id) {
        log.debug("Request to get HuyActor : {}", id);
        return huyActorRepository.findById(id);
    }

    /**
     * Delete the huyActor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HuyActor : {}", id);
        huyActorRepository.deleteById(id);
        huyActorSearchRepository.deleteById(id);
    }

    /**
     * Search for the huyActor corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyActor> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of HuyActors for query {}", query);
        return huyActorSearchRepository.search(queryStringQuery(query), pageable);    }
}

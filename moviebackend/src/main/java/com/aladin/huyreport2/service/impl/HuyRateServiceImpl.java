package com.aladin.huyreport2.service.impl;

import com.aladin.huyreport2.service.HuyRateService;
import com.aladin.huyreport2.domain.HuyRate;
import com.aladin.huyreport2.repository.HuyRateRepository;
import com.aladin.huyreport2.repository.search.HuyRateSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link HuyRate}.
 */
@Service
@Transactional
public class HuyRateServiceImpl implements HuyRateService {

    private final Logger log = LoggerFactory.getLogger(HuyRateServiceImpl.class);

    private final HuyRateRepository huyRateRepository;

    private final HuyRateSearchRepository huyRateSearchRepository;

    public HuyRateServiceImpl(HuyRateRepository huyRateRepository, HuyRateSearchRepository huyRateSearchRepository) {
        this.huyRateRepository = huyRateRepository;
        this.huyRateSearchRepository = huyRateSearchRepository;
    }

    /**
     * Save a huyRate.
     *
     * @param huyRate the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HuyRate save(HuyRate huyRate) {
        log.debug("Request to save HuyRate : {}", huyRate);
        HuyRate result = huyRateRepository.save(huyRate);
        huyRateSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the huyRates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyRate> findAll(Pageable pageable) {
        log.debug("Request to get all HuyRates");
        return huyRateRepository.findAll(pageable);
    }

    /**
     * Get one huyRate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HuyRate> findOne(Long id) {
        log.debug("Request to get HuyRate : {}", id);
        return huyRateRepository.findById(id);
    }

    /**
     * Delete the huyRate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HuyRate : {}", id);
        huyRateRepository.deleteById(id);
        huyRateSearchRepository.deleteById(id);
    }

    /**
     * Search for the huyRate corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HuyRate> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of HuyRates for query {}", query);
        return huyRateSearchRepository.search(queryStringQuery(query), pageable);    }
}

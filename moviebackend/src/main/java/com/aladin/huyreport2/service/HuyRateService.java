package com.aladin.huyreport2.service;

import com.aladin.huyreport2.domain.HuyRate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link HuyRate}.
 */
public interface HuyRateService {

    /**
     * Save a huyRate.
     *
     * @param huyRate the entity to save.
     * @return the persisted entity.
     */
    HuyRate save(HuyRate huyRate);

    /**
     * Get all the huyRates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyRate> findAll(Pageable pageable);

    /**
     * Get the "id" huyRate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HuyRate> findOne(Long id);

    /**
     * Delete the "id" huyRate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the huyRate corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyRate> search(String query, Pageable pageable);
}

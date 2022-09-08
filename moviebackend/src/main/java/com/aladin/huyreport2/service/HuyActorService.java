package com.aladin.huyreport2.service;

import com.aladin.huyreport2.domain.HuyActor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link HuyActor}.
 */
public interface HuyActorService {

    /**
     * Save a huyActor.
     *
     * @param huyActor the entity to save.
     * @return the persisted entity.
     */
    HuyActor save(HuyActor huyActor);

    /**
     * Get all the huyActors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyActor> findAll(Pageable pageable);

    /**
     * Get the "id" huyActor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HuyActor> findOne(Long id);

    /**
     * Delete the "id" huyActor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the huyActor corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyActor> search(String query, Pageable pageable);
}

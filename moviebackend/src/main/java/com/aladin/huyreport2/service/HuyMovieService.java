package com.aladin.huyreport2.service;

import com.aladin.huyreport2.domain.HuyMovie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link HuyMovie}.
 */
public interface HuyMovieService {

    /**
     * Save a huyMovie.
     *
     * @param huyMovie the entity to save.
     * @return the persisted entity.
     */
    HuyMovie save(HuyMovie huyMovie);

    /**
     * Get all the huyMovies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyMovie> findAll(Pageable pageable);

    /**
     * Get all the huyMovies with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<HuyMovie> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" huyMovie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HuyMovie> findOne(Long id);

    /**
     * Delete the "id" huyMovie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the huyMovie corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyMovie> search(String query, Pageable pageable);
}

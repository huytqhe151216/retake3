package com.aladin.huyreport2.service;

import com.aladin.huyreport2.domain.HuyGenre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link HuyGenre}.
 */
public interface HuyGenreService {

    /**
     * Save a huyGenre.
     *
     * @param huyGenre the entity to save.
     * @return the persisted entity.
     */
    HuyGenre save(HuyGenre huyGenre);

    /**
     * Get all the huyGenres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyGenre> findAll(Pageable pageable);

    /**
     * Get the "id" huyGenre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HuyGenre> findOne(Long id);

    /**
     * Delete the "id" huyGenre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the huyGenre corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HuyGenre> search(String query, Pageable pageable);
}

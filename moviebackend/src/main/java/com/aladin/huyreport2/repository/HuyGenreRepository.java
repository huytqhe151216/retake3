package com.aladin.huyreport2.repository;

import com.aladin.huyreport2.domain.HuyGenre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HuyGenre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HuyGenreRepository extends JpaRepository<HuyGenre, Long> {
}

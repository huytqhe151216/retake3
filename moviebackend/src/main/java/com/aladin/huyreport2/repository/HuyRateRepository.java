package com.aladin.huyreport2.repository;

import com.aladin.huyreport2.domain.HuyRate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HuyRate entity.
 */
@Repository
public interface HuyRateRepository extends JpaRepository<HuyRate, Long> {
}

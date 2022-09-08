package com.aladin.huyreport2.repository.search;

import com.aladin.huyreport2.domain.HuyRate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link HuyRate} entity.
 */
public interface HuyRateSearchRepository extends ElasticsearchRepository<HuyRate, Long> {
}

package com.aladin.huyreport2.repository.search;

import com.aladin.huyreport2.domain.HuyActor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link HuyActor} entity.
 */
public interface HuyActorSearchRepository extends ElasticsearchRepository<HuyActor, Long> {
}

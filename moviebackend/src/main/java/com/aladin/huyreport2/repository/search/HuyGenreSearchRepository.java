package com.aladin.huyreport2.repository.search;

import com.aladin.huyreport2.domain.HuyGenre;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link HuyGenre} entity.
 */
public interface HuyGenreSearchRepository extends ElasticsearchRepository<HuyGenre, Long> {
}

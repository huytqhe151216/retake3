package com.aladin.huyreport2.repository.search;

import com.aladin.huyreport2.domain.HuyMovie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link HuyMovie} entity.
 */
public interface HuyMovieSearchRepository extends ElasticsearchRepository<HuyMovie, Long> {
}

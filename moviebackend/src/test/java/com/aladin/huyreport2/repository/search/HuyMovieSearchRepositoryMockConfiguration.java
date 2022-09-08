package com.aladin.huyreport2.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link HuyMovieSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class HuyMovieSearchRepositoryMockConfiguration {

    @MockBean
    private HuyMovieSearchRepository mockHuyMovieSearchRepository;

}

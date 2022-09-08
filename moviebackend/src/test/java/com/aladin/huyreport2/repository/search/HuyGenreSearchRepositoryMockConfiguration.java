package com.aladin.huyreport2.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link HuyGenreSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class HuyGenreSearchRepositoryMockConfiguration {

    @MockBean
    private HuyGenreSearchRepository mockHuyGenreSearchRepository;

}

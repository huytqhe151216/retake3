package com.aladin.huyreport2.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link HuyRateSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class HuyRateSearchRepositoryMockConfiguration {

    @MockBean
    private HuyRateSearchRepository mockHuyRateSearchRepository;

}

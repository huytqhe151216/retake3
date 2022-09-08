package com.aladin.huyreport2.repository.search;

import com.aladin.huyreport2.domain.User;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {
}

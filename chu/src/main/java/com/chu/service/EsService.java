package com.chu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.chu.dao.entity.AuthUserEntity;

public interface EsService extends ElasticsearchRepository<AuthUserEntity, Long>{

    @Query("{\"bool\":{\"must\":[{\"field\":{\"cname\":\"?0\"}}]}}")
    Page<AuthUserEntity> findBySearchname(String cname, Pageable pageable);
}

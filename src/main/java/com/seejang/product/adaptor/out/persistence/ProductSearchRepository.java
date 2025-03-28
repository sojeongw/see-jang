package com.seejang.product.adaptor.out.persistence;

import com.seejang.product.domain.ProductSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearch, Long> {
    List<ProductSearch> findByName(String name);
}

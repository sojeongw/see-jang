package com.seejang.product.adaptor.out.persistence;

import com.seejang.product.domain.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findByName(String name);
}

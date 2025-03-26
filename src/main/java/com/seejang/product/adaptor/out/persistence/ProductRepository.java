package com.seejang.product.adaptor.out.persistence;

import com.seejang.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

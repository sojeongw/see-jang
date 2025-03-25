package com.seejang.product.entity;

import com.seejang.common.AggregateRoot;
import com.seejang.product.entity.ProductId.ProductIdType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JavaType;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends AggregateRoot<Product, ProductId> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JavaType(ProductIdType.class)
    private ProductId id;

    private String name;

    private Category category;

    @Embedded
    private Price regularPrice;

    @Embedded
    private Price shippingFee;

    private LocalDateTime deadline;
}

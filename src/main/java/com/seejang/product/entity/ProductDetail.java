package com.seejang.product.entity;

import com.seejang.common.DomainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JavaType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDetail extends DomainEntity<ProductDetail, ProductDetailId> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JavaType(ProductDetailId.ProductDetailIdType.class)
    private ProductDetailId id;

    private String content;
}

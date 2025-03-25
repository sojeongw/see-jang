package com.seejang.product.entity;

import com.seejang.common.LongTypeIdentifier;
import com.seejang.common.LongTypeIdentifierJavaType;

public class ProductDetailId extends LongTypeIdentifier {

    public static ProductDetailId of(Long id) {
        return new ProductDetailId(id);
    }

    public ProductDetailId(Long id) {
        super(id);
    }

    public static class ProductDetailIdType extends LongTypeIdentifierJavaType<ProductDetailId> {

        protected ProductDetailIdType() {
            super(ProductDetailId.class);
        }
    }
}

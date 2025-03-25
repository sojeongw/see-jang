package com.seejang.product.entity;

import com.seejang.common.LongTypeIdentifier;
import com.seejang.common.LongTypeIdentifierJavaType;

public class ProductId extends LongTypeIdentifier {

    public static ProductId of(Long id) {
        return new ProductId(id);
    }

    public ProductId(Long id) {
        super(id);
    }

    public static class ProductIdType extends LongTypeIdentifierJavaType<ProductId> {

        protected ProductIdType() {
            super(ProductId.class);
        }
    }
}

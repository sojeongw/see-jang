package com.seejang.product.application.port.in;

import com.seejang.product.domain.Product;

public record CreateProductDocumentEvent(
        Product product
) {
}

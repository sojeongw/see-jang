package com.seejang.product.application.service;

import com.seejang.product.adaptor.out.persistence.ProductDocumentRepository;
import com.seejang.product.application.port.in.SearchProductListQuery;
import com.seejang.product.application.port.out.SearchProductListResult;
import com.seejang.product.domain.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProductList {

    private final ProductDocumentRepository productSearchRepository;

    @Transactional(readOnly = true)
    public SearchProductListResult execute(SearchProductListQuery query) {
        List<ProductDocument> products = productSearchRepository.findByName(query.getName());

        return new SearchProductListResult(
                products.stream()
                        .map(product -> new SearchProductListResult.ProductResult(
                                        product.getId(),
                                        product.getName(),
                                        product.getCategory(),
                                        product.getDescription(),
                                        product.getRegularPrice().getAmount(),
                                        product.getShippingFee().getAmount(),
                                        product.getInventory().getQuantity(),
                                        product.getInventory().getStatus(),
                                        product.getDeadline(),
                                        product.getCreatedAt(),
                                        product.getUpdatedAt()
                                )
                        ).toList()
        );
    }
}

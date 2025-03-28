package com.seejang.product.adaptor.in;

import com.seejang.common.DefaultResponse;
import com.seejang.product.application.port.in.CreateProductCommand;
import com.seejang.product.application.port.in.SearchProductListQuery;
import com.seejang.product.application.port.out.CreateProductResult;
import com.seejang.product.application.port.out.SearchProductListResult;
import com.seejang.product.application.service.CreateProduct;
import com.seejang.product.application.service.SearchProductList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProduct createProduct;
    private final SearchProductList searchProductList;

    @PostMapping
    public DefaultResponse<CreateProductResult> createProduct(@RequestBody CreateProductCommand command) {
        CreateProductResult result = createProduct.execute(command);
        return DefaultResponse.successful(result);
    }

    @PostMapping("/search")
    public DefaultResponse<SearchProductListResult> getProducts(@RequestBody SearchProductListQuery query) {
        SearchProductListResult result = searchProductList.execute(query);
        return DefaultResponse.successful(result);
    }
}

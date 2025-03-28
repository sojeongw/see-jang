package com.seejang.product.application.port.in;

import com.seejang.common.SelfValidator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SearchProductListQuery extends SelfValidator<SearchProductListQuery> {

    @NotNull
    private final String name;

    public SearchProductListQuery(String name) {
        this.name = name;

        validateSelf();
    }
}

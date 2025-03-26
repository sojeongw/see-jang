package com.seejang.product.domain;

import com.seejang.common.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price extends ValueObject<Price> {

    private BigDecimal amount;

    public Price(BigDecimal amount) {
        this.amount = amount;
    }
}

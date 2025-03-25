package com.seejang.product.entity;

import com.seejang.common.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Embeddable
public class Price extends ValueObject<Price> {

    private BigDecimal amount;

}

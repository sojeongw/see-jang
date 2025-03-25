package com.seejang.product.entity;

import com.seejang.common.ValueObject;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Price extends ValueObject<Price> {

    private BigDecimal amount;

}

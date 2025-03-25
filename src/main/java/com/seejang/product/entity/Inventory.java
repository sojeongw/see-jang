package com.seejang.product.entity;

import com.seejang.common.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
@Embeddable
public class Inventory extends ValueObject<Inventory> {

    private Long quantity;

    @Enumerated(EnumType.STRING)
    private Status status;
}

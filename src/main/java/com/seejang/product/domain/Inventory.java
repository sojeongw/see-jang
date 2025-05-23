package com.seejang.product.domain;

import com.seejang.common.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory extends ValueObject<Inventory> {

    private Long quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Inventory(Long quantity, Status status) {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("수량이 적절하지 않습니다.");
        }

        this.quantity = quantity;
        this.status = status;
    }

    public boolean invalid() {
        return this.quantity == null || this.quantity < 0;
    }
}

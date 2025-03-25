package com.seejang.product.entity;

import com.seejang.common.AggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends AggregateRoot<Product, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "regular_price")),})
    private Price regularPrice;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "shipping_fee")),})
    private Price shippingFee;

    @Embedded
    private Inventory inventory;

    private LocalDateTime deadline;
}

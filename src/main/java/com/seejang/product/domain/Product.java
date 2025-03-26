package com.seejang.product.domain;

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

import java.math.BigDecimal;
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
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "regular_price"))})
    private Price regularPrice;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "shipping_fee"))})
    private Price shippingFee;

    @Embedded
    private Inventory inventory;

    private LocalDateTime deadline;

    private Product(String name, Category category, Price regularPrice, Price shippingFee, Inventory inventory, LocalDateTime deadline) {
        this.name = name;
        this.category = category;
        this.regularPrice = regularPrice;
        this.shippingFee = shippingFee;
        this.inventory = inventory;
        this.deadline = deadline;
    }

    public static Product of(String name, Category category, BigDecimal regularPrice, BigDecimal shippingFee, Long quantity, Status status, LocalDateTime deadline) {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("수량이 적절하지 않습니다.");
        }
        if (regularPrice == null || regularPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("가격이 적절하지 않습니다");
        }
        if (shippingFee == null || shippingFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("배송비가 적절하지 않습니다");
        }
        return new Product(name, category, new Price(regularPrice), new Price(shippingFee), new Inventory(quantity, status), deadline);
    }


}

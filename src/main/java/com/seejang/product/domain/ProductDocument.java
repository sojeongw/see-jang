package com.seejang.product.domain;

import com.seejang.common.AggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Getter
@Document(indexName = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDocument extends AggregateRoot<ProductDocument, String> {

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "regular_price"))})
    private Price regularPrice;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "shipping_fee"))})
    private Price shippingFee;

    @Embedded
    private Inventory inventory;

    private LocalDateTime deadline;

    private ProductDocument(String name, Category category, String description, Price regularPrice,
                            Price shippingFee, Inventory inventory, LocalDateTime deadline) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.regularPrice = regularPrice;
        this.shippingFee = shippingFee;
        this.inventory = inventory;
        this.deadline = deadline;
    }

    public static ProductDocument of(String name, Category category, String description, Price regularPrice,
                                     Price shippingFee, Inventory inventory, LocalDateTime deadline) {
        if (inventory.invalid()) {
            throw new IllegalArgumentException("수량이 적절하지 않습니다");
        }
        if (regularPrice.invalid()) {
            throw new IllegalArgumentException("가격이 적절하지 않습니다");
        }
        if (shippingFee.invalid()) {
            throw new IllegalArgumentException("배송비가 적절하지 않습니다");
        }
        return new ProductDocument(name, category, description, regularPrice, shippingFee, inventory, deadline);
    }
}

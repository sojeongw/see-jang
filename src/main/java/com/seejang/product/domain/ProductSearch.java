package com.seejang.product.domain;

import com.seejang.common.AggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Document(indexName = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSearch extends AggregateRoot<ProductSearch, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private ProductSearch(String name, Category category, String description, Price regularPrice, Price shippingFee,
                          Inventory inventory, LocalDateTime deadline) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.regularPrice = regularPrice;
        this.shippingFee = shippingFee;
        this.inventory = inventory;
        this.deadline = deadline;
    }

    public static ProductSearch of(String name, Category category, String description, BigDecimal regularPrice, BigDecimal shippingFee,
                                   Long quantity, Status status, LocalDateTime deadline) {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("수량이 적절하지 않습니다.");
        }
        if (regularPrice == null || regularPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("가격이 적절하지 않습니다");
        }
        if (shippingFee == null || shippingFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("배송비가 적절하지 않습니다");
        }
        return new ProductSearch(name, category, description, new Price(regularPrice), new Price(shippingFee), new Inventory(quantity, status), deadline);
    }
}

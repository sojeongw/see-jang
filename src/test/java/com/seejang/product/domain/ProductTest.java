package com.seejang.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DisplayName("Product")
class ProductTest {

    @Test
    @DisplayName("상품을 생성할 수 있다")
    void create() {
        String name = "삼다수 500ml";
        Product result = Product.of(name, Category.NORMAL, BigDecimal.valueOf(1000), BigDecimal.valueOf(2500),
                100L, Status.AVAILABLE, null);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("수량이 마이너스면 상품을 생성할 수 없다")
    void quantity_illegal_exception() {
        assertThatThrownBy(() -> Product.of("삼다수 500ml", Category.NORMAL, BigDecimal.valueOf(1000),
                BigDecimal.valueOf(2500), -1L, Status.AVAILABLE, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPrices")
    @DisplayName("가격이 없거나 마이너스면 상품을 생성할 수 없다")
    void regular_price_illegal_exception(BigDecimal price) {
        assertThatThrownBy(() -> Product.of("삼다수 500ml", Category.NORMAL, price,
                BigDecimal.valueOf(2500), 100L, Status.AVAILABLE, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPrices")
    @DisplayName("배송비가 없거나 마이너스면 상품을 생성할 수 없다")
    void shipping_fee_illegal_exception(BigDecimal fee) {
        assertThatThrownBy(() -> Product.of("삼다수 500ml", Category.NORMAL, BigDecimal.valueOf(1000),
                fee, 100L, Status.AVAILABLE, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideInvalidPrices() {
        return Stream.of(
                Arguments.of((BigDecimal) null),
                Arguments.of(BigDecimal.valueOf(-1))
        );
    }
}
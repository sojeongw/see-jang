package com.seejang.product.application.service;

import com.seejang.product.adaptor.out.persistence.ProductRepository;
import com.seejang.product.application.port.in.CreateProductCommand;
import com.seejang.product.application.port.in.CreateProductDocumentEvent;
import com.seejang.product.application.port.out.CreateProductResult;
import com.seejang.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Create Product")
class CreateProductTest {

    @InjectMocks
    private CreateProduct createProduct;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CreateProductCommand command;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Nested
    @DisplayName("수량이")
    class Quantity {

        @Test
        @DisplayName("없거나 마이너스면 상품을 생성할 수 없다")
        void quantity_IllegalArgumentException() {
            assertThatThrownBy(() -> createProduct.execute(command))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Nested
        @DisplayName("있고 가격이")
        class RegularPrice {

            @BeforeEach
            void setUp() {
                when(command.getQuantity()).thenReturn(100L);
            }

            @Test
            @DisplayName("없거나 마이너스면 상품을 생성할 수 없다")
            void regular_price_IllegalArgumentException() {
                assertThatThrownBy(() -> createProduct.execute(command))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @Nested
            @DisplayName("있고 배송비가")
            class ShippingFee {

                @BeforeEach
                void setUp() {
                    when(command.getRegularPrice()).thenReturn(BigDecimal.valueOf(1000));
                }

                @Test
                @DisplayName("없거나 마이너스면 상품을 생성할 수 없다")
                void regular_price_IllegalArgumentException() {
                    assertThatThrownBy(() -> createProduct.execute(command))
                            .isInstanceOf(IllegalArgumentException.class);
                }

                @Test
                @DisplayName("있으면 상품을 생성할 수 있다")
                void create_product() {
                    when(command.getShippingFee()).thenReturn(BigDecimal.valueOf(2500));
                    CreateProductResult result = createProduct.execute(command);

                    verify(productRepository, times(1)).save(any(Product.class));
                    verify(eventPublisher, times(1)).publishEvent(any(CreateProductDocumentEvent.class));
                    assertThat(result.regularPrice()).isEqualTo(command.getRegularPrice());
                    assertThat(result.shippingFee()).isEqualTo(command.getShippingFee());
                }
            }
        }
    }

}
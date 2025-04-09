package com.seejang.product.application.service;

import com.seejang.product.adaptor.out.persistence.ProductRepository;
import com.seejang.product.application.port.in.CreateProductCommand;
import com.seejang.product.application.port.in.CreateProductDocumentEvent;
import com.seejang.product.application.port.out.CreateProductResult;
import com.seejang.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProduct {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public CreateProductResult execute(CreateProductCommand command) {
        Product product = Product.of(command.getName(), command.getCategory(), command.getDescription(),
                command.getRegularPrice(), command.getShippingFee(), command.getQuantity(), command.getStatus(), command.getDeadline());

        eventPublisher.publishEvent(new CreateProductDocumentEvent(product));
        productRepository.save(product);
        return new CreateProductResult(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getRegularPrice().getAmount(),
                product.getShippingFee().getAmount(),
                product.getInventory().getQuantity(),
                product.getInventory().getStatus(),
                product.getDeadline(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}

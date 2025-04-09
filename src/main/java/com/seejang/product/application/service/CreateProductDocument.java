package com.seejang.product.application.service;

import com.seejang.product.adaptor.out.persistence.ProductDocumentRepository;
import com.seejang.product.application.port.in.CreateProductDocumentEvent;
import com.seejang.product.domain.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductDocument {

    private final ProductDocumentRepository productDocumentRepository;

    @Async
    @EventListener
//    @Transactional
    public void execute(CreateProductDocumentEvent event) {
        System.out.println("event = " + event);
        ProductDocument productDocument = ProductDocument.of(
                event.product().getName(),
                event.product().getCategory(),
                event.product().getDescription(),
                event.product().getRegularPrice(),
                event.product().getShippingFee(),
                event.product().getInventory(),
                event.product().getDeadline()
        );

        productDocumentRepository.save(productDocument);
        System.out.println("productDocument = " + productDocument);
    }
}

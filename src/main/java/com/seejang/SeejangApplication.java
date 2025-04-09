package com.seejang;

import com.seejang.product.adaptor.out.persistence.ProductDocumentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = ProductDocumentRepository.class))
@SpringBootApplication
public class SeejangApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeejangApplication.class, args);
    }

}

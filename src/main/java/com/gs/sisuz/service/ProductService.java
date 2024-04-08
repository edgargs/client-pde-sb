package com.gs.sisuz.service;

import com.gs.sisuz.model.Product;
import com.gs.sisuz.model.ProductId;
import com.gs.sisuz.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findByCodProd(String codGrupoCia, String codProd) {
        return productRepository.findByCodProd(codGrupoCia, codProd);
    }
}

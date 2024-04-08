package com.gs.sisuz.repository;

import com.gs.sisuz.model.Product;
import com.gs.sisuz.model.ProductId;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ProductRepository extends ListCrudRepository<Product, ProductId> {

    @Query("""
            SELECT *
            FROM LGT_PROD
            WHERE COD_GRUPO_CIA = :codGrupoCia
                AND COD_PROD = :codProd
            """)
    Product findByCodProd(String codGrupoCia, String codProd);
}

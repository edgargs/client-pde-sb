package com.gs.sisuz.repository;

import com.gs.sisuz.model.Product;
import com.gs.sisuz.model.ProductId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ProductRepository extends ListCrudRepository<Product, ProductId> {

    @Query(value = """
            SELECT *
            FROM LGT_PROD
            WHERE COD_GRUPO_CIA = :codGrupoCia
                AND COD_PROD = :codProd
            """,
            nativeQuery = true)
    Product findByCodProd(String codGrupoCia, String codProd);
}

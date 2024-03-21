package com.gs.sisuz.repository;

import com.gs.sisuz.model.CompanyId;
import com.gs.sisuz.model.Enterprise;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface EnterpriseRepository extends ListCrudRepository<Enterprise, CompanyId> {
    @Query("SELECT * FROM neg_empresa WHERE cod_grupo_cia = :codGrupoCia AND cod_cia = :codCia")
    Enterprise findByCodGrupoCiaAndCodCia(String codGrupoCia, String codCia);
}

package com.gs.sisuz.model;

import org.springframework.data.relational.core.mapping.Column;

public record CompanyId(

        //@Column("cod_grupo_cia")
        String codGrupoCia,
        //@Column("cod_cia")
        String codCia
) {
}

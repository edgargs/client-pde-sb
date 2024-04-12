package com.gs.sisuz.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record CompanyId(

        //@Column("cod_grupo_cia")
        String codGrupoCia,
        //@Column("cod_cia")
        String codCia
) {
}

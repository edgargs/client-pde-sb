package com.gs.sisuz.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductId(
        String codGrupoCia,
        String codProd
) {
}

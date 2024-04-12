package com.gs.sisuz.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record OrderDetailId(
        String codGrupoCia,

        String codLocal,

        String numPedVta,

        int secPedVtaDet
) {
}

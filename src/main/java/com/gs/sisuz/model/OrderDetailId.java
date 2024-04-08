package com.gs.sisuz.model;

public record OrderDetailId(
        String codGrupoCia,

        String codLocal,

        String numPedVta,

        int secPedVtaDet
) {
}

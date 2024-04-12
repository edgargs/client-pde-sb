package com.gs.sisuz.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record PaymentVoucherId(
        String codGrupoCia,

        String codLocal,

        String numPedVta,

        String secCompPago
) {
}

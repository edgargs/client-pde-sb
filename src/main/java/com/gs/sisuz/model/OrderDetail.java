package com.gs.sisuz.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name="VTA_PEDIDO_VTA_DET")
public record OrderDetail(
        @EmbeddedId OrderDetailId id,
        String codProd,
        int cantAtendida,
        double sinIgvBaseValPrecVta,
        double valVtaItemE,
        double valPrecVtaUnitE,
        String codTipAfecIgvE,
        double valIgv,
        double valTotalIgvItemE,
        double valPrecTotal
) {
    public double getTotalImpuestos() {
        return valTotalIgvItemE;
    }
}

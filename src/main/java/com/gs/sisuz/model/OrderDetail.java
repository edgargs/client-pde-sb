package com.gs.sisuz.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("VTA_PEDIDO_VTA_DET")
public record OrderDetail(
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

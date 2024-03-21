package com.gs.sisuz.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("VTA_COMP_PAGO")
public record PaymentVoucher(
        double totalGravE,
        double totalInafE,
        double totalExonE,
        double totalGratuE,
        double valIgvCompPago,
        double valNetoCompPago,
        double valRedondeoCompPago
) {
    public double valTotalImpuestos() {
        return Math.abs(valIgvCompPago);
    }

    public double valValorVenta() {
        return Math.abs(valNetoCompPago - valIgvCompPago);
    }

    public double valTotalVenta() {
        return Math.abs(valNetoCompPago) + valRedondeoCompPago; //TODO round(2)
    }
}

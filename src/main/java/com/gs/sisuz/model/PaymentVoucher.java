package com.gs.sisuz.model;

import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Table("VTA_COMP_PAGO")
public record PaymentVoucher(
        String tipCompPago,
        String cePrefijo,
        String ceSerie,
        String ceCorrelativo,
        Date fecCreaCompPago,
        String codTipMoneda,
        String codTipIdentRecepE,
        String numDocImpr,
        String nomImprComp,
        String direcImprComp,
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

    public String valTipoDocumentoSunat() {
        switch(tipCompPago) {
            case "02":
            case "06": return "01"; //FACTURA
            case "01":
            case "05": return "03"; //BOLETA
            case "04": return "07"; //NC
            default: return tipCompPago;
        }
    }

    public String valTipoMonedaSunat() {
        if(codTipMoneda == null){
            return "PEN";
        }
        switch(codTipMoneda) {
            case "01": return "PEN";
            case "02": return "USD";
            default: return "XXX";
        }
    }

    public String valNumeroDocumento() {
        return numDocImpr.trim().equals("")?"88888888":numDocImpr;
    }

    public String valNombreImpreso() {
        return nomImprComp.trim().equals("")?"CLIENTE VARIOS":nomImprComp;
    }
}

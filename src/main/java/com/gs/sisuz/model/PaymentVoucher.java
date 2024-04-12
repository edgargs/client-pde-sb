package com.gs.sisuz.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.sql.Date;

@Entity(name="VTA_COMP_PAGO")
public record PaymentVoucher(
        @EmbeddedId PaymentVoucherId id,
        String tipCompPago,
        String cePrefijo,
        String ceSerie,
        String ceCorrelativo,
        Date fecCreaCompPago,
        String codTipMoneda,
        @Column(name = "cod_tip_ident_recep_e") String codTipIdentRecepE,
        String numDocImpr,
        String nomImprComp,
        String direcImprComp,
        @Column(name = "total_grav_e") double totalGravE,
        @Column(name = "total_inaf_e") double totalInafE,
        @Column(name = "total_exon_e") double totalExonE,
        @Column(name = "total_gratu_e") double totalGratuE,
        double valIgvCompPago,
        double valNetoCompPago,
        double valRedondeoCompPago,
        String ceEstadoEnvioSunat,
        String ceDescripcionObservado,
        String externalId
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

    public PaymentVoucher updateEnvioSunat(String ceEstadoEnvioSunat, String ceDescripcionObservado, String externalId) {
        return new PaymentVoucher(
                id(),
                tipCompPago(),
                cePrefijo(),
                ceSerie(),
                ceCorrelativo(),
                fecCreaCompPago(),
                codTipMoneda(),
                codTipIdentRecepE(),
                numDocImpr(),
                nomImprComp(),
                direcImprComp(),
                totalGravE(),
                totalInafE(),
                totalExonE(),
                totalGratuE(),
                valIgvCompPago(),
                valNetoCompPago(),
                valRedondeoCompPago(),
                ceEstadoEnvioSunat,
                ceDescripcionObservado,
                externalId
        );
    }
}

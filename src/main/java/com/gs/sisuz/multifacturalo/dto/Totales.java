package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Totales {
    @JsonProperty("total_exportacion")
    private double exportacion;
    @JsonProperty("total_operaciones_gravadas")
    private double operacionesGravadas;
    @JsonProperty("total_operaciones_inafectas")
    private double operacionesInafectas;
    @JsonProperty("total_operaciones_exoneradas")
    private double operacionesExoneradas;
    @JsonProperty("total_operaciones_gratuitas")
    private double operacionesGratuitas;
    @JsonProperty("total_igv")
    private double igv;
    @JsonProperty("total_impuestos")
    private double impuestos;
    @JsonProperty("total_valor")
    private double valor;
    @JsonProperty("total_venta")
    private double venta;

    public Totales(double exportacion, double operacionesGravadas, double operacionesInafectas, double operacionesExoneradas, double operacionesGratuitas, double igv, double impuestos, double valor, double venta) {
        this.exportacion = exportacion;
        this.operacionesGravadas = operacionesGravadas;
        this.operacionesInafectas = operacionesInafectas;
        this.operacionesExoneradas = operacionesExoneradas;
        this.operacionesGratuitas = operacionesGratuitas;
        this.igv = igv;
        this.impuestos = impuestos;
        this.valor = valor;
        this.venta = venta;
    }

    public double getExportacion() {
        return exportacion;
    }

    public void setExportacion(double exportacion) {
        this.exportacion = exportacion;
    }

    public double getOperacionesGravadas() {
        return operacionesGravadas;
    }

    public void setOperacionesGravadas(double operacionesGravadas) {
        this.operacionesGravadas = operacionesGravadas;
    }

    public double getOperacionesInafectas() {
        return operacionesInafectas;
    }

    public void setOperacionesInafectas(double operacionesInafectas) {
        this.operacionesInafectas = operacionesInafectas;
    }

    public double getOperacionesExoneradas() {
        return operacionesExoneradas;
    }

    public void setOperacionesExoneradas(double operacionesExoneradas) {
        this.operacionesExoneradas = operacionesExoneradas;
    }

    public double getOperacionesGratuitas() {
        return operacionesGratuitas;
    }

    public void setOperacionesGratuitas(double operacionesGratuitas) {
        this.operacionesGratuitas = operacionesGratuitas;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }
}

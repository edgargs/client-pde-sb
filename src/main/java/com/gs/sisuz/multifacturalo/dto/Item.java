package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("codigo_interno")
    private String codigoInterno;
    private String descripcion;
    @JsonProperty("codigo_producto_sunat")
    private String codigoProductoSunat;
    @JsonProperty("unidad_de_medida")
    private String unidadMedida;
    private int cantidad;
    @JsonProperty("valor_unitario")
    private double valorUnitario;
    @JsonProperty("codigo_tipo_precio")
    private String codigoTipoPrecio;
    @JsonProperty("precio_unitario")
    private double precioUnitario;
    @JsonProperty("codigo_tipo_afectacion_igv")
    private String tipoAfectacionIgv;
    @JsonProperty("total_base_igv")
    private double totalBaseIgv;
    @JsonProperty("porcentaje_igv")
    private double porcentajeIgv;
    @JsonProperty("total_igv")
    private double totalIgv;
    @JsonProperty("total_impuestos")
    private double totalImpuestos;
    @JsonProperty("total_valor_item")
    private double totalValorItem;
    @JsonProperty("total_item")
    private double totalItem;

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoProductoSunat() {
        return codigoProductoSunat;
    }

    public void setCodigoProductoSunat(String codigoProductoSunat) {
        this.codigoProductoSunat = codigoProductoSunat;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getCodigoTipoPrecio() {
        return codigoTipoPrecio;
    }

    public void setCodigoTipoPrecio(String codigoTipoPrecio) {
        this.codigoTipoPrecio = codigoTipoPrecio;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getTipoAfectacionIgv() {
        return tipoAfectacionIgv;
    }

    public void setTipoAfectacionIgv(String tipoAfectacionIgv) {
        this.tipoAfectacionIgv = tipoAfectacionIgv;
    }

    public double getTotalBaseIgv() {
        return totalBaseIgv;
    }

    public void setTotalBaseIgv(double totalBaseIgv) {
        this.totalBaseIgv = totalBaseIgv;
    }

    public double getPorcentajeIgv() {
        return porcentajeIgv;
    }

    public void setPorcentajeIgv(double porcentajeIgv) {
        this.porcentajeIgv = porcentajeIgv;
    }

    public double getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(double totalIgv) {
        this.totalIgv = totalIgv;
    }

    public double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public double getTotalValorItem() {
        return totalValorItem;
    }

    public void setTotalValorItem(double totalValorItem) {
        this.totalValorItem = totalValorItem;
    }

    public double getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(double totalItem) {
        this.totalItem = totalItem;
    }
}

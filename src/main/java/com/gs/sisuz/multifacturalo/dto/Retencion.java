package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Retencion {

    private String codigo;
    private double porcentaje;
    private double monto;
    private double base;
    @JsonProperty("currency_type_id")
    private String tipoMoneda;
    @JsonProperty("exchange_rate")
    private int exchangeRate;
    @JsonProperty("amount_pen")
    private double montoPen;
    @JsonProperty("amount_usd")
    private double montoUsd;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public int getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(int exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getMontoPen() {
        return montoPen;
    }

    public void setMontoPen(double montoPen) {
        this.montoPen = montoPen;
    }

    public double getMontoUsd() {
        return montoUsd;
    }

    public void setMontoUsd(double montoUsd) {
        this.montoUsd = montoUsd;
    }
}

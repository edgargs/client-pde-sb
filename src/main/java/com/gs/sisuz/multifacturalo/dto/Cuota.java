package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cuota {

    private String fecha;
    @JsonProperty("codigo_tipo_moneda")
    private String tipoMoneda;
    private double monto;
    @JsonProperty("codigo_metodo_de_pago")
    private String condigoMetodoDePago;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCondigoMetodoDePago() {
        return condigoMetodoDePago;
    }

    public void setCondigoMetodoDePago(String condigoMetodoDePago) {
        this.condigoMetodoDePago = condigoMetodoDePago;
    }
}

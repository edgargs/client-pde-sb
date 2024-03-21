package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatosEmisor {
    @JsonProperty("codigo_del_domicilio_fiscal")
    private String domicilioFiscal;

    public DatosEmisor(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }

    public String getDomicilioFiscal() {
        return domicilioFiscal;
    }

    public void setDomicilioFiscal(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }
}

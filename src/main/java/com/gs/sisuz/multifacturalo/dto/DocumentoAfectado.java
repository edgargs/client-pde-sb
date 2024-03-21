package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentoAfectado {

    @JsonProperty("external_id")
    private String externalId;

    public DocumentoAfectado(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}

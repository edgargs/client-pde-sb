package com.gs.sisuz.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name="LGT_PROD")
public record Product(
        @EmbeddedId ProductId id,
        String descProd,
        String descUnidPresent
) {
    public String valUnidadMedidaSunat() {
        switch(descUnidPresent){
            case "UNIDAD": return "NIU";
            case "SERVICIO": return "NIU";
            default: return "NIU";
        }
    }
}

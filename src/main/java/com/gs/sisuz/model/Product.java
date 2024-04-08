package com.gs.sisuz.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("LGT_PROD")
public record Product(
        @Id @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL) ProductId id,
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

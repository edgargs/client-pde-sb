package com.gs.sisuz.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "NEG_EMPRESA")
public record Enterprise(
        @EmbeddedId CompanyId id,

        String url,
        String token
) {
}

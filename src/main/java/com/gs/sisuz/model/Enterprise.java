package com.gs.sisuz.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("NEG_EMPRESA")
public record Enterprise(
        @Id @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL) CompanyId id,

        String url,
        String token
) {
}

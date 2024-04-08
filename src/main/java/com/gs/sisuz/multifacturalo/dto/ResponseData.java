package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ResponseData(
        String number,
        @JsonAlias({"external_id"})
        String externalId
) {
}

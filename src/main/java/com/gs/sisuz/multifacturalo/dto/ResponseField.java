package com.gs.sisuz.multifacturalo.dto;

public record ResponseField(
        String code,
        String description,
        String[] notes
) {
}

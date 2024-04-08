package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ResponseFact (
    boolean success,
    String message,
    ResponseData data,
    ResponseField response
){
    public ResponseFact(boolean success, String message) {
        this(success, message,
                null, null);
    }
}


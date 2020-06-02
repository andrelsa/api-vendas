package com.dev.andrelsa.domain.enums;

import java.util.Arrays;

public enum TypeStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    private String code;

    public String getCode() {
        return code;
    }

    public static TypeStatus of(String value) {
        return Arrays.stream(TypeStatus.values()).filter(v -> value.equals(v.getCode())).findFirst().orElse(null);
    }

    TypeStatus(String code) {
        this.code = code;
    }

    public String getName() {
        return name();
    }

}

package com.cg.model.enums;


public enum EUserRole {
    ROLE_ADMIN("ADMIN"),
    ROLE_CUSTOMER("CUSTOMER");

    private final String value;

    EUserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}

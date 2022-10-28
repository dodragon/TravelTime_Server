package com.dod.traveltime.entity.data;

public enum CarType implements EnumCodeValue {
    MINE("Mine", "자차"),
    RENT("Rent", "렌트"),
    TRANS("Transfer", "대중교통"),
    NONE("No", "없음");

    private String code;
    private String value;

    CarType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}

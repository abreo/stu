package com.nihao.model.enums;

/**
 * Created by nihao on 16/10/18.
 */
public enum LogTypeEnum {
    ON_LINE(1,"上线"),
    OFF_LINE(2,"下线");
    private Integer value;
    private String description;

    LogTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}

package com.nihao.model.enums;

/**
 * Created by nihao on 16/10/18.
 */
public enum ResourceTypeEnum {
    MAIN_PAGE(1,"主页面"),
    SUB_PAGE(2,"子页面"),
    FUNCTION_BUTT(3,"功能OR按钮");
    private Integer value;
    private String description;
    ResourceTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

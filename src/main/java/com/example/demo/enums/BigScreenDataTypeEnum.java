package com.example.demo.enums;

/**
 * 大屏数据类型
 */
public enum BigScreenDataTypeEnum {

    riskPointCount("riskPointCount", "用户统计"),
    riskPointMapList("riskPointMapList", "风险点列表");

    public String idType;
    public String typeName;

    private BigScreenDataTypeEnum(String iType, String typeName) {
        this.idType = iType;
        this.typeName = typeName;
    }

    public String getIdType() {
        return this.idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}



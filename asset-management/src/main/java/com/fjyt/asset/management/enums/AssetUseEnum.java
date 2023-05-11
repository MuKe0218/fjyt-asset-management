package com.fjyt.asset.management.enums;

/**
 * @author keQiLong
 * @date 2023年05月10日 11:12
 * 领用状态枚举
 */
public enum AssetUseEnum {

    USE("0","已领用"),EXAMINE_AND_APPROVE("1","审批中"),
    EXAMINE_AND_APPROVE_NO("2","审批未通过"),STOCK_RETURN("3","已退库");

    private final String code;
    private final String info;

    AssetUseEnum(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}

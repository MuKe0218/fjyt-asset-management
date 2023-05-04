package com.fjyt.asset.management.enums;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:53
 * 资产状态枚举
 */
public enum AssetEnum {

    IDLE("0","空闲"),USE("1","使用中"),BORROW("2","借用中"),EXAMINE_AND_APPROVE("3","审批中"),
    MAINTENANCE("4","维修中"),SCRAP("5","报废");

    private final String code;
    private final String info;

    AssetEnum(String code, String info)
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

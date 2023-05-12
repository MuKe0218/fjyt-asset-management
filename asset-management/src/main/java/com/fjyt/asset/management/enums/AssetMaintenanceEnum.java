package com.fjyt.asset.management.enums;

/**
 * @author keQiLong
 * @date 2023年05月12日 11:13
 * 维修状态枚举
 */
public enum AssetMaintenanceEnum {
    MAINTENANCE("0","维修中"),EXAMINE_AND_APPROVE("1","审批中"),
    EXAMINE_AND_APPROVE_NO("2","审批未通过"),MAINTENANCE_COMPLETE("3","维修完成");

    private final String code;
    private final String info;

    AssetMaintenanceEnum(String code, String info)
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

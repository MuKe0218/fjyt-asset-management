package com.fjyt.asset.management.enums;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:29
 */
public enum ProcureStatusEnum {
    EXAMINE_AND_APPROVE("0","审批中"),EXAMINE_AND_APPROVE_NO("1","审批未通过"),
        EXAMINE_AND_APPROVE_OK("2","审批通过");

    private final String code;
    private final String info;

    ProcureStatusEnum(String code, String info)
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

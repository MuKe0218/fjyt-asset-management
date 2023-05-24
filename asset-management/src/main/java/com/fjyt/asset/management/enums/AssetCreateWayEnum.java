package com.fjyt.asset.management.enums;

/**
 * @author keQiLong
 * @date 2023年05月05日 10:50
 */
public enum AssetCreateWayEnum {
    HAND_MOVEMENT("0","手动创建"),FROM_DINGING("1","来自钉钉");

    private final String code;
    private final String info;

    AssetCreateWayEnum(String code, String info)
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

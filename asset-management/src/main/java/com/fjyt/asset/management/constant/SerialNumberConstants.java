package com.fjyt.asset.management.constant;

/**
 * @author keQiLong
 * @date 2023年05月05日 14:17
 * 流水号通用常量
 */
public class SerialNumberConstants {
    /**
     * 资产前缀
     */
    public static final String ASSET_ZC = "ZC";
    /**
     * 资产领用前缀
     */
    public static final String ASSET_LY = "LY";
    /**
     * 资产借用前缀
     */
    public static final String ASSET_JY = "JY";
    /**
     * 资产维修前缀
     */
    public static final String ASSET_WX = "WX";
    /**
     * 资产报废前缀
     */
    public static final String ASSET_BF = "BF";
    /**
     * 出库单前缀
     */
    public static final String WAREHOUSE_CK = "CK";
    /**
     * 采购单前缀
     */
    public static final String WAREHOUSE_CG = "CG";
    /**
     * 入库单前缀
     */
    public static final String WAREHOUSE_RK = "RK";
    /**
     * 存储redis键值
     */
    public static final String SERIAL_NUMBER = "serial_number";
    /**
     * 存储redis中时间键值
     */
    public static final String SERIAL_NUMBER_DATE = "serial_number:date";
    /**
     * 存储redis中编号键值
     */
    public static final String SERIAL_NUMBER_NUM = "serial_number:num";
}

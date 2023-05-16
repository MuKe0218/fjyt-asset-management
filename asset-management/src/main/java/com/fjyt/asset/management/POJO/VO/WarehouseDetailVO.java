package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月15日 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDetailVO {
    /**
     * 仓库id
     */
    private Long id;
    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 省份id
     */
    private String provinceId;
    /**
     * 城市id
     */
    private String cityId;
    /**
     * 区县id
     */
    private String countyId;
    /**
     * 具体地址
     */
    private String specificPosition;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;
    /**
     * 仓库状态 0：启用；1：禁用
     */
    private String status;
}

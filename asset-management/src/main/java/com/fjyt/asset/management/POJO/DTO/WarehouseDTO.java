package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月15日 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {
    /**
     * id
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
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private String status;
}

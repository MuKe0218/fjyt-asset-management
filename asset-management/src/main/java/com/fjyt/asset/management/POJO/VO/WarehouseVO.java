package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月15日 14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseVO {
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
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 仓库状态 0：启用；1：禁用
     */
    private String status;
}

package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月12日 10:42
 * 维修表对应类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetMaintenance implements Serializable {
    private static final long serializable = 1L;
    /**
     * 资产维修单ID
     */
    private Long id;
    /**
     * 资产维修单编码
     */
    private String maintenanceCode;
    /**
     * 资产编码 JSON格式
     */
    private String assetCodes;
    /**
     * 资产报修人
     */
    private String maintenanceUser;
    /**
     * 资产报修申请时间
     */
    private Date maintenanceTime;
    /**
     * 资产维修备注
     */
    private String remark;
    /**
     * 资产报修单创建人
     */
    private String createUser;
    /**
     * 资产报修单创建时间
     */
    private Date createTime;
    /**
     * 资产报修单创建方式 0：手动创建；1：关联钉钉物品报修申请自动创建
     */
    private String createWay;
    /**
     * 资产报修单修改人
     */
    private String updateUser;
    /**
     * 资产报修单修改时间
     */
    private Date updateTime;
    /**
     * 维修状态 0：维修中；1：审批中；2：审批中；3：维修完成
     */
    private String status;
    /**
     * 逻辑删除 0：未删除；1：已删除
     */
    private String maintenanceDel;
}

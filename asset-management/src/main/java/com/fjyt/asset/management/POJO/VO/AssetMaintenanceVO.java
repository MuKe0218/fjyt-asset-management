package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月12日 11:00
 * 返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetMaintenanceVO {
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
     * 资产names
     */
    private String assetNames;
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
}

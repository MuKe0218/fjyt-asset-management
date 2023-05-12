package com.fjyt.asset.management.POJO.VO;

import com.fjyt.asset.management.POJO.DO.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月12日 11:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDetailVO {
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
     * 资产维修备注
     */
    private String remark;
    /**
     * 维修资产列表
     */
    private List<AssetVO> assetSelectList;
}

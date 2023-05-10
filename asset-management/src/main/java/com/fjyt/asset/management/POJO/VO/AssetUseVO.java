package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月10日 9:44
 * 领用返回类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetUseVO {
    /**
     * 资产领用单ID
     */
    private Long id;
    /**
     * 资产领用单编码
     */
    private String useCode;
    /**
     * 资产编码list
     */
    private String assetCodes;
    /**
     * 领用资产名称字符串集合
     */
    private String assetNames;
    /**
     * 资产领用人/申请人
     */
    private String useUser;
    /**
     * 申请时间
     */
    private Date useTime;
    /**
     * 资产领用备注
     */
    private String remark;
    /**
     * 资产领用单修改人
     */
    private String createUser;
    /**
     * 资产领用单修改时间
     */
    private Date createTime;
    /**
     * 资产领用单修改人
     */
    private String updateUser;
    /**
     * 资产领用单修改时间
     */
    private Date updateTime;
    /**
     * 资产领用单创建方式 0：手动创建；1：关联钉钉物品领用申请自动创建
     */
    private String createWay;
    /**
     * 领用状态 0：已领用；1：审批中；2：审批未通过；
     */
    private String status;
}

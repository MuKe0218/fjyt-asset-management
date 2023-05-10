package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月09日 17:36
 * 领用表对应类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetUse implements Serializable {
    private static final long serializable = 1L;
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
     * 资产领用单创建人
     */
    private String createUser;
    /**
     * 资产领用单创建时间
     */
    private Date createTime;
    /**
     * 资产领用单创建方式 0：手动创建；1：关联钉钉物品领用申请自动创建
     */
    private String createWay;
    /**
     * 资产领用单修改人
     */
    private String updateUser;
    /**
     * 资产领用单修改时间
     */
    private Date updateTime;
    /**
     * 领用状态 0：已领用；1：审批中；2：审批未通过；
     */
    private String status;
    /**
     * 逻辑删除 0：未删除；1：已删除
     */
    private String useDel;
}

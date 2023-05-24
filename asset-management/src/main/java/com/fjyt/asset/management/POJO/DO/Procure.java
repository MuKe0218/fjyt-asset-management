package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Procure implements Serializable {
    private static final long serializable = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 采购编码
     */
    private String procureCode;
    /**
     * 采购物品信息
     */
    private String procureAssets;
    /**
     * 资产codes
     */
    private String assetCodes;
    /**
     * 采购申请人
     */
    private String procureUser;
    /**
     * 采购申请时间
     */
    private Date procureTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 验收人
     */
    private String checkUser;
    /**
     * 验收时间
     */
    private Date checkTime;
    /**
     * 创建方式
     */
    private String createWay;
    /**
     * 采购单创建人
     */
    private String createUser;
    /**
     * 采购单创建时间
     */
    private Date createTime;
    /**
     * 采购单修改人
     */
    private String updateUser;
    /**
     * 采购单修改时间
     */
    private Date updateTime;
    /**
     * 采购状态 0：采购审批中；1：审批未通过；2：审批通过
     */
    private String status;
    /**
     * 逻辑删除 0：未删除；1：已删除
     */
    private String procureDel;
}

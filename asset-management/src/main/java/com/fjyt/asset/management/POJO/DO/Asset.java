package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 资产Id
     */
    private Long id;
    /**
     * 资产编码
     */
    private String assetCode;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产规格
     */
    private String assetSpecifications;
    /**
     * 资产计量单位
     */
    private String assetUnit;
    /**
     * 资产分类
     */
    private Long classifyId;
    /**
     * 资产价格
     */
    private Float assetPrice;
    /**
     * 资产备注
     */
    private String remark;
    /**
     * 资产创建人
     */
    private String createUser;
    /**
     * 资产创建时间
     */
    private Date createTime;
    /**
     * 资产创建方式 0：手动创建；1：关联钉钉采购申请自动创建
     */
    private String createWay;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 资产状态 0：空闲；1：使用中；2：借用中；3：审批中；4：维修中；5：报废
     */
    private String status;
    /**
     *  资产逻辑删除 0：未删除；1：已删除
     */
    private String  assetDel;
}

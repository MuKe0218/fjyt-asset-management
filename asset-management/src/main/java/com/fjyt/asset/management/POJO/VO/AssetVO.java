package com.fjyt.asset.management.POJO.VO;

import lombok.Data;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年04月27日 16:27
 * 资产信息返回对象
 */
@Data
public class AssetVO {
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
     * 分类id
     */
    private Long classifyId;
    /**
     * 资产分类
     */
    private String classifyName;
    /**
     * 资产计量单位
     */
    private String assetUnit;
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
     * 资产使用/借用/报修人
     */
    private String assetHaveUser;
    /**
     * 资产使用/借用/报修时间
     */
    private Date assetHaveTime;
    /**
     * 资产状态 0：空闲；1：使用中；2：借用中；3：审批中；4：维修中；5：报废
     */
    private String status;
}

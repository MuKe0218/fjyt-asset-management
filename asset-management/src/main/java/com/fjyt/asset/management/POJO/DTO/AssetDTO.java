package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年04月27日 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {
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
    private Long assetClassify;
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
    /**
     * 分页pageNum
     */
    private int pageNum;
    /**
     * 分页pageSize
     */
    private int pageSize;
}

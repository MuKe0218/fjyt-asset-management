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
     * 资产id
     */
    private Long id;
    /**
     * 资产流水号
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
     * 搜索资产创建开始时间
     */
    private String beginCreateTime;
    /**
     * 搜索资产创建结束时间
     */
    private String endCreateTime;
    /**
     * 资产创建方式 0：手动创建；1：关联钉钉采购申请自动创建
     */
    private String createWay;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 搜索修改开始时间
     */
    private String beginUpdateTime;
    /**
     * 搜索修改开始时间
     */
    private String endUpdateTime;
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

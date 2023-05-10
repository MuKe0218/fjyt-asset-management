package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月10日 11:54
 * 资产查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetQueryDTO {
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
     * 资产分类
     */
    private Long classifyId;
    /**
     * 资产状态 0：空闲；1：使用中；2：借用中；3：审批中；4：维修中；5：报废
     */
    private String status;
    /**
     * 资产创建方式 0：手动创建；1：关联钉钉采购申请自动创建
     */
    private String createWay;
    /**
     * 搜索资产创建开始时间
     */
    private String beginCreateTime;
    /**
     * 搜索资产创建结束时间
     */
    private String endCreateTime;
    /**
     * 搜索修改开始时间
     */
    private String beginUpdateTime;
    /**
     * 搜索修改开始时间
     */
    private String endUpdateTime;
    /**
     * 分页pageNum
     */
    private int pageNum;
    /**
     * 分页pageSize
     */
    private int pageSize;
}

package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月10日 14:38
 * 资产详情
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDetailVO {
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
     * 分类id
     */
    private Long classifyId;
    /**
     * 资产分类
     */
    private String classifyName;
    /**
     * 资产状态 0：空闲；1：使用中；2：借用中；3：审批中；4：维修中；5：报废
     */
    private String status;
    /**
     * 资产规格
     */
    private String assetSpecifications;
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
     * 资产创建方式 0：手动创建；1：关联钉钉采购申请自动创建
     */
    private String createWay;
    /**
     * 资产创建人
     */
    private String createUser;
    /**
     * 资产创建时间
     */
    private Date createTime;
    /**
     * 流动信息
     */
//    private List<> assetFlows;
}

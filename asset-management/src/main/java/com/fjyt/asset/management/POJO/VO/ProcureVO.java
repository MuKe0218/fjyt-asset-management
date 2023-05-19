package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcureVO {
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
     * 物品信息名称集合
     */
    private String assetNames;
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
     * 状态
     */
    private String status;
}

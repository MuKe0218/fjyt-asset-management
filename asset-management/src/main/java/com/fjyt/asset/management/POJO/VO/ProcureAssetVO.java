package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcureAssetVO {
    /**
     * 物品名称
     */
    private String assetName;
    /**
     * 规格/型号
     */
    private String assetSpecifications;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 数量
     */
    private Long num;
    /**
     * 金额
     */
    private Float price;
}

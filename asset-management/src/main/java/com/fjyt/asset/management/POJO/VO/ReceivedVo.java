package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月18日 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivedVo {
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 类别
     */
    private String classifyName;
    /**
     * 规格型号
     */
    private String assetSpecifications;
    /**
     * 计量单位
     */
    private String assetUnit;
    /**
     * 总金额
     */
    private Float totalPrice;
    /**
     * 数量
     */
    private String num;
}

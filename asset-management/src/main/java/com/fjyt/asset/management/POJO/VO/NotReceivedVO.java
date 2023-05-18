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
public class NotReceivedVO {
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
     * 资产分类
     */
    private String classifyName;
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
}

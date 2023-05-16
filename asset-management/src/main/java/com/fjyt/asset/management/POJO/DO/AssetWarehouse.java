package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author keQiLong
 * @date 2023年05月16日 9:47
 * 资产与仓库关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetWarehouse implements Serializable {
    private static final long serializable = 1L;
    /**
     * 资产id
     */
    private Long assetId;
    /**
     * 仓库id
     */
    private Long warehouseId;
}

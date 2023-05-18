package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月18日 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetWarehousingDTO {
    /**
     * 资产id
     */
    private Long id;
    /**
     * 仓库id
     */
    private Long warehouseId;
}

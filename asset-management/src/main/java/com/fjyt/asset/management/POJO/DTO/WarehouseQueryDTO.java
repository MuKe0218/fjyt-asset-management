package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月15日 14:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseQueryDTO {
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 分页pageNum
     */
    private int pageNum;
    /**
     * 分页pageSize
     */
    private int pageSize;
}

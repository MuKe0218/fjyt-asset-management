package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月18日 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingQueryDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 入库单Code
     */
    private String warehousingCode;
    /**
     * 分页页码
     */
    private int pageNum;
    /**
     * 每页个数
     */
    private int pageSize;
}

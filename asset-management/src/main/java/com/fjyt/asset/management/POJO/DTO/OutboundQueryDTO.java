package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月18日 15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundQueryDTO {
    /**
     * id
     */
    private Long id;
    /**
     *
     */
    private String outboundCode;
    /**
     * 分页页码
     */
    private int pageNum;
    /**
     * 每页个数
     */
    private int pageSize;
}

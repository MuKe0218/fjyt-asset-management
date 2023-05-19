package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcureQueryDTO {
    /**
     * 采购编码
     */
    private String procureCode;
    /**
     * 申请人
     */
    private String procureUser;
    /**
     * 采购申请开始时间
     */
    private String beginProcureTime;
    /**
     * 采购申请结束时间
     */
    private String endProcureTime;
    /**
     * 分页页码
     */
    private int pageNum;
    /**
     * 分页每页数
     */
    private int pageSize;
}

package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月10日 9:51
 * 领用查询dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UseQueryDTO {
    /**
     *  领用单编号
     */
    private String useCode;
    /**
     * 申请人
     */
    private String useUser;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建方式
     */
    private String createWay;
    /**
     * 申请开始时间
     */
    private String beginUseTime;
    /**
     * 申请结束时间
     */
    private String endUseTime;
    /**
     * 创建开始时间
     */
    private String beginCreateTime;
    /**
     * 创建结束时间
     */
    private String endCreateTime;
    /**
     * 分页pageNum
     */
    private int pageNum;
    /**
     * 分页pageSize
     */
    private int pageSize;
}

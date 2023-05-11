package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月11日 14:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowQueryDTO {
    /**
     * 借用单id
     */
    private Long id;
    /**
     * 借用单编号
     */
    private String borrowCode;
    /**
     * 借用人及申请人
     */
    private String borrowUser;
    /**
     * 借用状态 0：已借用；1：审批中；2：审批未通过；3：已归还
     */
    private String status;
    /**
     * 创建方式
     */
    private String createWay;
    /**
     * 借用开始时间 begin时间
     */
    private String beginBorrowStartTime;
    /**
     * 借用开始时间 end时间
     */
    private String endBorrowStartTime;
    /**
     * 预计归还时间 begin时间
     */
    private String beginExpectedReturnTime;
    /**
     * 预计归还时间 end时间
     */
    private String endExpectedReturnTime;
    /**
     * 创建时间 begin
     */
    private String beginCreateTime;
    /**
     * 创建时间 end
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

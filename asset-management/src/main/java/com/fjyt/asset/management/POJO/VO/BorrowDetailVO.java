package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月11日 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDetailVO {
    /**
     * 借用单id
     */
    private Long id;
    /**
     * 借用单编号
     */
    private String borrowCode;
    /**
     * 借用资产code
     */
    private String assetCodes;
    /**
     * 借用人及申请人
     */
    private String borrowUser;
    /**
     * 申请时间
     */
    private Date borrowTime;
    /**
     * 借用开始时间
     */
    private Date borrowStartTime;
    /**
     * 预计归还时间
     */
    private Date expectedReturnTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 借用状态 0：已借用；1：审批中；2：审批未通过；3：已归还
     */
    private String status;
    /**
     * 创建方式
     */
    private String createWay;
    /**
     * 资产列表
     */
    private List<AssetVO> assetSelectList;

}

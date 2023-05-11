package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月11日 14:29
 * 对应借用信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetBorrow implements Serializable {
    private static final long serializable = 1L;
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
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建方式
     */
    private String createWay;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 借用状态 0：已借用；1：审批中；2：审批未通过；3：已归还
     */
    private String status;
    /**
     * 逻辑删除 0：未删除；1：已删除
     */
    private String borrowDel;

}

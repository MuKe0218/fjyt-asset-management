package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月10日 10:45
 * 领用详情VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UseDetailVO {
    /**
     * 资产领用单ID
     */
    private Long id;
    /**
     * 资产领用单编码
     */
    private String useCode;
    /**
     * 资产领用人/申请人
     */
    private String useUser;
    /**
     * 申请时间
     */
    private Date useTime;
    /**
     * 资产领用备注
     */
    private String remark;
    /**
     * 领用状态 0：已领用；1：审批中；2：审批未通过；
     */
    private String status;
    /**
     * 资产编码list
     */
    private String assetCodes;
    /**
     * 资产列表
     */
    private List<AssetVO> assetSelectList;
}

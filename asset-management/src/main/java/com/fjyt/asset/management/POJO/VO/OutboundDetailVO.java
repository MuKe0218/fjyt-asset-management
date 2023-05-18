package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundDetailVO {
    /**
     * id
     */
    private Long id;
    /**
     * 出库单编号
     */
    private String outboundCode;
    /**
     * 资产编码集合
     */
    private String assetCodes;
    /**
     * 出库时间
     */
    private Date outboundTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 资产列表
     */
    private List<AssetVO> outboundAssetList;

}

package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 16:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingDetailVO {
    /**
     * id
     */
    private Long id;
    /**
     * 资产编码集合
     */
    private String assetCodes;
    /**
     * 入库编号
     */
    private String warehousingCode;
    /**
     * 入库时间
     */
    private Date warehousingTime;
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
    private List<AssetVO> warehousingAssetList;
}

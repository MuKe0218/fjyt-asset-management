package com.fjyt.asset.management.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月18日 16:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingVO {
    /**
     * id
     */
    private Long id;
    /**
     * 资产编码集合
     */
    private String assetCodes;
    /**
     * 资产名称s
     */
    private String assetNames;
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
     * 编辑人
     */
    private String updateUser;
    /**
     * 编辑时间
     */
    private Date updateTime;
}

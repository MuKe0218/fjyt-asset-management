package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月18日 15:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetWarehousing implements Serializable {
    public static final long serializable = 1L;
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
     * 编辑人
     */
    private String updateUser;
    /**
     * 编辑时间
     */
    private Date updateTime;
    /**
     * 逻辑删除 0：未删除；1：已删除
     */
    private String warehousingDel;
}

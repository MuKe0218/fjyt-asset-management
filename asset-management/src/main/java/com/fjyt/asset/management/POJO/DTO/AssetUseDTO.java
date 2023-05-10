package com.fjyt.asset.management.POJO.DTO;

import com.fjyt.asset.management.POJO.DO.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月09日 17:25
 * 领用dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetUseDTO {
    /**
     * id
     */
    private Long id;
    /**
     *  领用单编号
     */
    private String useCode;
    /**
     * 申请人
     */
    private String useUser;
    /**
     * 申请时间
     */
    private Date useTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建方式
     */
    private String createWay;
    /**
     * 资产对象List
     */
    private List<Asset> assetSelectList;
}

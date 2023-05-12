package com.fjyt.asset.management.POJO.DTO;

import com.fjyt.asset.management.POJO.DO.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月12日 10:50
 * 新增修改对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetMaintenanceDTO {
    /**
     * 资产维修单ID
     */
    private Long id;
    /**
     * 资产维修单编码
     */
    private String maintenanceCode;
    /**
     * 资产报修人
     */
    private String maintenanceUser;
    /**
     * 资产报修申请时间
     */
    private Date maintenanceTime;
    /**
     * 资产维修备注
     */
    private String remark;
    /**
     * 维修资产列表
     */
    private List<Asset> assetSelectList;
}

package com.fjyt.asset.management.POJO.DTO;

import com.fjyt.asset.management.POJO.DO.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月11日 14:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetBorrowDTO {
    /**
     * 借用单id
     */
    private Long id;
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
     * 资产对象List
     */
    private List<Asset> assetSelectList;
}

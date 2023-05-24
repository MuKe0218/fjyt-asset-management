package com.fjyt.asset.management.POJO.VO;

import com.fjyt.asset.management.POJO.DTO.DingDingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcureDetailVO {
    /**
     * id
     */
    private Long id;
    /**
     * 采购编码
     */
    private String procureCode;
    /**
     * 采购物品信息
     */
    private String procureAssets;
    /**
     * 资产codes
     */
    private String assetCodes;
    /**
     * 采购申请人
     */
    private String procureUser;
    /**
     * 采购申请时间
     */
    private Date procureTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 采购资产列表
     */
    private List<Map<String,String>> mapList;
}

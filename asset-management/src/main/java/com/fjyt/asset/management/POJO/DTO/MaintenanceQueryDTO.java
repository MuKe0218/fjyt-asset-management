package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月12日 10:53
 * 查询dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceQueryDTO {
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
     * 资产报修单创建方式 0：手动创建；1：关联钉钉物品报修申请自动创建
     */
    private String createWay;
    /**
     * 维修状态 0：维修中；1：审批中；2：审批中；3：维修完成
     */
    private String status;
    /**
     * 申请开始时间
     */
    private String beginMaintenanceTime;
    /**
     * 申请结束时间
     */
    private String endMaintenanceTime;
    /**
     * 创建开始时间
     */
    private String beginCreateTime;
    /**
     * 创建结束时间
     */
    private String endCreateTime;
    /**
     * 分页页码
     */
    private int pageNum;
    /**
     * 每页个数
     */
    private int pageSize;
}

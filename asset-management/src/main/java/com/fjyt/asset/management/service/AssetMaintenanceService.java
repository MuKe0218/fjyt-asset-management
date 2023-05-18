package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DTO.AssetMaintenanceDTO;
import com.fjyt.asset.management.POJO.DTO.MaintenanceQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetMaintenanceVO;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月12日 11:27
 */
public interface AssetMaintenanceService {
    /**
     * 查询维修列表
     * @param maintenanceQueryDTO
     * @return
     */
     List<AssetMaintenanceVO> maintenanceList(MaintenanceQueryDTO maintenanceQueryDTO);

    /**
     * 根据id获取维修信息
     * @param id
     * @return
     */
     R getMaintenanceById(Long id);

    /**
     * 新增维修信息
     * @param assetMaintenanceDTO
     * @return
     */
     R addMaintenance(AssetMaintenanceDTO assetMaintenanceDTO);

    /**
     * 修改维修信息
     * @param assetMaintenanceDTO
     * @return
     */
     R updateMaintenance(AssetMaintenanceDTO assetMaintenanceDTO);

    /**
     * 根据id删除维修信息
     * @param id
     * @return
     */
     R delMaintenance(Long id);
}

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
    public List<AssetMaintenanceVO> maintenanceList(MaintenanceQueryDTO maintenanceQueryDTO);

    /**
     * 根据id获取维修信息
     * @param id
     * @return
     */
    public R getMaintenanceById(Long id);

    /**
     * 新增维修信息
     * @param assetMaintenanceDTO
     * @return
     */
    public R addMaintenance(AssetMaintenanceDTO assetMaintenanceDTO);

    /**
     * 修改维修信息
     * @param assetMaintenanceDTO
     * @return
     */
    public R updateMaintenance(AssetMaintenanceDTO assetMaintenanceDTO);

    /**
     * 根据id删除维修信息
     * @param id
     * @return
     */
    public R delMaintenance(Long id);
}

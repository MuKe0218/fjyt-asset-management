package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.AssetMaintenance;
import com.fjyt.asset.management.POJO.DTO.AssetMaintenanceDTO;
import com.fjyt.asset.management.POJO.DTO.MaintenanceQueryDTO;
import com.fjyt.asset.management.POJO.VO.AssetMaintenanceVO;
import com.fjyt.asset.management.POJO.VO.MaintenanceDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月12日 11:26
 */
@Mapper
public interface AssetMaintenanceMapper {
    /**
     * 查询维修信息列表
     * @param maintenanceQueryDTO
     * @return
     */
     List<AssetMaintenanceVO> maintenanceList(MaintenanceQueryDTO maintenanceQueryDTO);

    /**
     * 根据id查询维修信息
     * @param id
     * @return
     */
     MaintenanceDetailVO getMaintenanceById(Long id);

    /**
     * 新增维修信息
     * @param assetMaintenance
     */
     void addMaintenance(AssetMaintenance assetMaintenance);

    /**
     * 修改维修信息
     * @param assetMaintenance
     */
     void updateMaintenance(AssetMaintenance assetMaintenance);

    /**
     * 删除维修信息
     * @param id
     */
     void delMaintenance(Long id);
}

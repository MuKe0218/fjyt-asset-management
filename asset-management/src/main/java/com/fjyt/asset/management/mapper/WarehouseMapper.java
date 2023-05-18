package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.AssetWarehouse;
import com.fjyt.asset.management.POJO.DO.Warehouse;
import com.fjyt.asset.management.POJO.DTO.WarehouseDTO;
import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.VO.WarehouseDetailVO;
import com.fjyt.asset.management.POJO.VO.WarehouseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月15日 14:12
 */
@Mapper
public interface WarehouseMapper {
    /**
     * 查询仓库列表
     * @param warehouseQueryDTO
     * @return
     */
     List<WarehouseVO> warehouseList(WarehouseQueryDTO warehouseQueryDTO);

    /**
     * 查询仓库列表没有分页
     * @return
     */
     List<WarehouseVO> warehouseListWithoutPage();

    /**
     * 查询根据仓库信息
     * @param id
     * @return
     */
     WarehouseDetailVO warehouseInfo(Long id);

    /**
     * 根据仓库code获取资产
     * @param warehouseId
     * @return
     */
     List<AssetWarehouse> assetAndWarehouseByWareCode(Long warehouseId);

    /**
     * 校验仓库是否存在
     * @param warehouseName
     * @return
     */
     Warehouse checkByWarehouseName(String warehouseName);

    /**
     * 新增仓库
     * @param warehouse
     */
     void addWarehouse(Warehouse warehouse);

    /**
     * 修改仓库
     * @param warehouse
     */
     void updateWarehouse(Warehouse warehouse);

    /**
     * 修改状态
     * @param warehouse
     */
     void updateStatus(Warehouse warehouse);

    /**
     * 删除仓库
     * @param id
     */
     void deleteWarehouse(Long id);
}

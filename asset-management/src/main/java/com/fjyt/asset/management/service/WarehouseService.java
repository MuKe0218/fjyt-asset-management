package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DTO.WarehouseDTO;
import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.WarehouseDetailVO;
import com.fjyt.asset.management.POJO.VO.WarehouseVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月15日 14:16
 */
public interface WarehouseService {
    /**
     * 查询仓库列表
     * @param warehouseQueryDTO
     * @return
     */
    public List<WarehouseVO> warehouseList(WarehouseQueryDTO warehouseQueryDTO);

    /**
     *
     * 查询仓库列表没有分页
     * @return
     */
    public R warehouseListWithoutPage();

    /**
     * 根据id查询仓库信息
     * @param id
     * @return
     */
    public WarehouseDetailVO warehouseInfo(Long id);

    /**
     * 新增仓库
     * @param warehouseDTO
     * @return
     */
    public R addWarehouse(WarehouseDTO warehouseDTO);

    /**
     * 编辑仓库
     * @param warehouseDTO
     * @return
     */
    public R updateWarehouse(WarehouseDTO warehouseDTO);

    /**
     * 修改状态
     * @param warehouseDTO
     * @return
     */
    public R updateStatus(WarehouseDTO warehouseDTO);

    /**
     * 根据仓库ID删除仓库
     * @param id
     * @return
     */
    public R deleteWarehouse(Long id);
}

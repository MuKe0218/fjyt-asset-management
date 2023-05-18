package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.WarehouseDTO;
import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.WarehouseVO;
import com.fjyt.asset.management.service.WarehouseService;
import com.fjyt.asset.management.utils.AssetUtils;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月15日 9:59
 * 仓库控制层
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    /**
     * 查询仓库列表
     * @param warehouseQueryDTO
     * @return
     */
    @GetMapping
    public TableDataInfo warehouseList(WarehouseQueryDTO warehouseQueryDTO){
        PageHelper.startPage(warehouseQueryDTO.getPageNum(),warehouseQueryDTO.getPageSize());
        List<WarehouseVO> warehouseVOS = warehouseService.warehouseList(warehouseQueryDTO);
        return new PageHelperUtil().resultInfo(warehouseVOS);
    }
    /**
     * 查询仓库列表
     *
     * @return
     */
    @GetMapping("/list")
    public R warehouseListWithoutPage(){
        return warehouseService.warehouseListWithoutPage();
    }
    /**
     * 根据id查询仓库信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R warehouseInfo(@PathVariable Long id){
        return R.ok(warehouseService.warehouseInfo(id));
    }

    /**
     * 新增仓库
     * @param warehouseDTO
     * @return
     */
    @PostMapping
    public R addWarehouse(@RequestBody WarehouseDTO warehouseDTO){
        return warehouseService.addWarehouse(warehouseDTO);
    }

    /**
     * 修改仓库
     * @param warehouseDTO
     * @return
     */
    @PutMapping
    public R updateWarehouse(@RequestBody WarehouseDTO warehouseDTO){
        return warehouseService.updateWarehouse(warehouseDTO);
    }

    /**
     * 修改状态
     * @param warehouseDTO
     * @return
     */
    @PutMapping("/changeStatus")
    public R updateStatus(@RequestBody WarehouseDTO warehouseDTO){
        return warehouseService.updateStatus(warehouseDTO);
    }
    /**
     * 删除仓库
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteWarehouse(@PathVariable Long id){
        return warehouseService.deleteWarehouse(id);
    }
}

package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.AssetMaintenanceDTO;
import com.fjyt.asset.management.POJO.DTO.MaintenanceQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.AssetMaintenanceVO;
import com.fjyt.asset.management.service.AssetMaintenanceService;
import com.fjyt.asset.management.utils.AssetUtils;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月17日 15:05
 * 资产维修Controller
 */
@RestController
@RequestMapping("/maintenance")
public class AssetMaintenanceController {


    @Autowired
    private AssetMaintenanceService assetMaintenanceService;
    /**
     * 查询维修列表
     * @param maintenanceQueryDTO
     * @return
     */
    @GetMapping
    public TableDataInfo maintenanceList(MaintenanceQueryDTO maintenanceQueryDTO){
        PageHelper.startPage(maintenanceQueryDTO.getPageNum(), maintenanceQueryDTO.getPageSize());
        List<AssetMaintenanceVO> assetMaintenanceVOS = assetMaintenanceService.maintenanceList(maintenanceQueryDTO);
        return new PageHelperUtil().resultInfo(assetMaintenanceVOS);
    }

    /**
     * 根据id获取维修信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getMaintenanceById(@PathVariable Long id){
        return assetMaintenanceService.getMaintenanceById(id);
    }

    /**
     * 新增维修信息
     * @param assetMaintenanceDTO
     * @return
     */
    @PostMapping
    public R addMaintenance(@RequestBody AssetMaintenanceDTO assetMaintenanceDTO){
        return assetMaintenanceService.addMaintenance(assetMaintenanceDTO);
    }

    /**
     * 修改维修信息
     * @param assetMaintenanceDTO
     * @return
     */
    @PutMapping
    public R updateMaintenance(@RequestBody AssetMaintenanceDTO assetMaintenanceDTO){
        return assetMaintenanceService.updateMaintenance(assetMaintenanceDTO);
    }

    /**
     * 根据id删除维修信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R delMaintenance(@PathVariable Long id){
        return assetMaintenanceService.delMaintenance(id);
    }

    /**
     * 获取状态信息列表
     * @return
     */
    @GetMapping("/status")
    public R maintenanceStatusList(){
        return R.ok(AssetUtils.maintenanceStatusList());
    }
}

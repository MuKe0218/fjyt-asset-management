package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.AssetUseDTO;
import com.fjyt.asset.management.POJO.DTO.UseQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.AssetUseVO;
import com.fjyt.asset.management.service.AssetUseService;
import com.fjyt.asset.management.utils.AssetUtils;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月17日 15:01
 * 资产领用Controller
 */
@RestController
@RequestMapping("/assetUse")
public class AssetUseController {

    @Autowired
    private AssetUseService assetUseService;

    /**
     * 查询领用列表
     * @param useQueryDTO
     * @return
     */
    @GetMapping
    public TableDataInfo useList(UseQueryDTO useQueryDTO){
        PageHelper.startPage(useQueryDTO.getPageNum(),useQueryDTO.getPageSize());
        List<AssetUseVO> assetUseVOS = assetUseService.useList(useQueryDTO);
        return new PageHelperUtil().resultInfo(assetUseVOS);
    }

    /**
     * 根据id查询领用详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R useListById(@PathVariable Long id){
        return assetUseService.useListById(id);
    }

    /**
     * 获取状态列表
     * @return
     */
    @GetMapping("/status")
    public R useStatusList(){
        return R.ok(AssetUtils.useStatusList());
    }

    /**
     * 新增领用
     * @param assetUseDTO
     * @return
     */
    @PostMapping
    public R addUse(@RequestBody AssetUseDTO assetUseDTO){
        return assetUseService.addUse(assetUseDTO);
    }

    /**
     * 修改领用
     * @param assetUseDTO
     * @return
     */
    @PutMapping
    public R updateUse(@RequestBody AssetUseDTO assetUseDTO){
        return assetUseService.updateUse(assetUseDTO);
    }

    /**
     * 根据id删除领用
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteUseById(@PathVariable Long id){
        return assetUseService.deleteUseById(id);
    }

    /**
     * 退库操作
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public R stockReturn(@PathVariable Long id){
        return assetUseService.stockReturn(id);
    }
}

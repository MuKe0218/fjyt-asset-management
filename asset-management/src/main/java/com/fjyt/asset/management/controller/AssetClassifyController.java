package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DO.AssetClassify;
import com.fjyt.asset.management.POJO.DTO.AssetClassifyDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetClassifyVO;
import com.fjyt.asset.management.service.AssetClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月04日 10:01
 * 资产分类Controller
 */
@RestController
@RequestMapping("/classify")
public class AssetClassifyController {

    @Autowired
    private AssetClassifyService assetClassifyService;
    /**
     * 查询分类列表
     * @param assetClassify
     * @return
     */
    @GetMapping("/list")
    public R classifyList(AssetClassify assetClassify){
        List<AssetClassifyVO> list = assetClassifyService.assetClassifyList(assetClassify);
        return R.ok(list,"查询成功");
    }

    /**
     * 添加分类
     * @param assetClassifyDTO
     * @return
     */
    @PostMapping
    public R classifyAdd(@RequestBody AssetClassifyDTO assetClassifyDTO){
        return assetClassifyService.assetClassifyAdd(assetClassifyDTO);
    }

    /**
     * 修改分类
     * @param assetClassifyDTO
     * @return
     */
    @PutMapping
    public R classifyUpdate(@RequestBody AssetClassifyDTO assetClassifyDTO){
        return null;
    }

    /**
     * 删除分类
     * @param classifyId
     * @return
     */
    @DeleteMapping("/{classifyId}")
    public R classifyDelete(@PathVariable Long classifyId){
        return assetClassifyService.assetClassifyDel(classifyId);
    }
}

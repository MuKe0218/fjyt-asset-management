package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.ProcureQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.ProcureVO;
import com.fjyt.asset.management.service.ProcureService;
import com.fjyt.asset.management.utils.AssetUtils;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:24
 */
@RestController
@RequestMapping("/procure")
public class ProcureController {

    @Autowired
    private ProcureService procureService;
    /**
     * 获取采购信息列表
     * @param procureQueryDTO
     * @return
     */
    @GetMapping
    public TableDataInfo getProcureList(ProcureQueryDTO procureQueryDTO){
        PageHelper.startPage(procureQueryDTO.getPageNum(), procureQueryDTO.getPageSize());
        List<ProcureVO> procureList = procureService.getProcureList(procureQueryDTO);
        return new PageHelperUtil().resultInfo(procureList);
    }

    /**
     * 根据id获取采购信息详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getProcureDetail(@PathVariable Long id){
        return procureService.getProcureDetail(id);
    }

    /**
     * 获取状态列表
     * @return
     */
    @GetMapping("/status")
    public R getStatusList(){
        return R.ok(AssetUtils.procureStatusList());
    }

    /**
     * 采购验收
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public R checkAndAccept(@PathVariable Long id){
        return procureService.checkAndAccept(id);
    }
}

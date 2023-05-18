package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.DTO.WarehousingQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.WarehousingVO;
import com.fjyt.asset.management.service.WarehousingService;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 16:03
 */
@RestController
@RequestMapping("/warehousing")
public class WarehousingController {

    @Autowired
    private WarehousingService warehousingService;

    /**
     * 获取入库单列表
     * @param warehousingQueryDTO
     * @return
     */
     @GetMapping
    public TableDataInfo getWarehousingList(WarehousingQueryDTO warehousingQueryDTO){
         PageHelper.startPage(warehousingQueryDTO.getPageNum(),warehousingQueryDTO.getPageSize());
         List<WarehousingVO> warehousingList = warehousingService.getWarehousingList(warehousingQueryDTO);
         return new PageHelperUtil().resultInfo(warehousingList);
     }

    /**
     * 根据id获取入库单详情
     * @param id
     * @return
     */
     @GetMapping("{id}")
    public R getWarehousingDetail(@PathVariable Long id){
         return warehousingService.getWarehousingDetail(id);
     }
}

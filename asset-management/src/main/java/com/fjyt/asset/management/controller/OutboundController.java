package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.OutboundQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.OutboundVO;
import com.fjyt.asset.management.service.OutboundService;
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
 * @date 2023年05月18日 15:27
 */
@RestController
@RequestMapping("/outbound")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    /**
     * 获取出库单列表
     * @param outboundQueryDTO
     * @return
     */
    @GetMapping
    public TableDataInfo getOutboundList(OutboundQueryDTO outboundQueryDTO){
        PageHelper.startPage(outboundQueryDTO.getPageNum(), outboundQueryDTO.getPageSize());
        List<OutboundVO> outboundList = outboundService.getOutboundList(outboundQueryDTO);
        return new PageHelperUtil().resultInfo(outboundList);
    }

    @GetMapping("{id}")
    public R getOutboundDetail(@PathVariable Long id){
        return outboundService.getOutboundDetail(id);
    }
}

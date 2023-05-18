package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DTO.OutboundDTO;
import com.fjyt.asset.management.POJO.DTO.OutboundQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.OutboundDetailVO;
import com.fjyt.asset.management.POJO.VO.OutboundVO;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 15:29
 */
public interface OutboundService {
    /**
     * 获取出库单列表
     * @param outboundQueryDTO
     * @return
     */
     List<OutboundVO> getOutboundList(OutboundQueryDTO outboundQueryDTO);

    /**
     * 根据id获取出库单详情
     * @param id
     * @return
     */
     R getOutboundDetail(Long id);

    /**
     * 新增出库单
     * @param outboundDTO
     */
     void addOutbound(OutboundDTO outboundDTO);
}

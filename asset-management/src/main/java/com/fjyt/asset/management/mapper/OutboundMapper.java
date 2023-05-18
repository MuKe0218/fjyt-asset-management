package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.AssetOutbound;
import com.fjyt.asset.management.POJO.DTO.OutboundQueryDTO;
import com.fjyt.asset.management.POJO.VO.OutboundDetailVO;
import com.fjyt.asset.management.POJO.VO.OutboundVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 15:29
 */
@Mapper
public interface OutboundMapper {
    /**
     * 获取出库单列表
     * @param outboundQueryDTO
     * @return
     */
    List<OutboundVO> getOutboundList(OutboundQueryDTO outboundQueryDTO);

    /**
     * 根据id获取出库单详情
     * @param id
     */
    OutboundDetailVO getOutboundDetail(Long id);

    /**
     * 新增出库单
     * @param assetOutbound
     */
    void addOutbound(AssetOutbound assetOutbound);
}

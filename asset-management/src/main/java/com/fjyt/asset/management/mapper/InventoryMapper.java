package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DTO.AssetWarehousingDTO;
import com.fjyt.asset.management.POJO.DTO.NotReceivedQueryDTO;
import com.fjyt.asset.management.POJO.DTO.ReceivedQueryDTO;
import com.fjyt.asset.management.POJO.VO.NotReceivedVO;
import com.fjyt.asset.management.POJO.VO.ReceivedVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 10:14
 */
@Mapper
public interface InventoryMapper {
    /**
     * 获取已入库的资产列表
     * @param receivedDTO
     * @return
     */
    public List<ReceivedVo> getReceivedAssetList(ReceivedQueryDTO receivedDTO);

    /**
     * 获取未入库的资产列表
     * @param notReceiveDTO
     * @return
     */
    public List<NotReceivedVO> getNotReceivedAssetList(NotReceivedQueryDTO notReceiveDTO);

    /**
     * 资产入库操作
     * @param assetWarehousingDTO
     */
    public void assetWarehousing(AssetWarehousingDTO assetWarehousingDTO);
}

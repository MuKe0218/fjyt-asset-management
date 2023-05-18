package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DTO.AssetWarehousingDTO;
import com.fjyt.asset.management.POJO.DTO.NotReceivedQueryDTO;
import com.fjyt.asset.management.POJO.DTO.ReceivedQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.NotReceivedVO;
import com.fjyt.asset.management.POJO.VO.ReceivedVo;
import com.fjyt.asset.management.mapper.InventoryMapper;
import com.fjyt.asset.management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 10:14
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    /**
     * 获取已入库的资产列表
     * @param receivedDTO
     * @return
     */
    @Override
    public R getReceivedAssetList(ReceivedQueryDTO receivedDTO) {
        List<ReceivedVo> receivedAssetList = inventoryMapper.getReceivedAssetList(receivedDTO);
        return R.ok(receivedAssetList);
    }

    /**
     * 获取未入库的资产列表
     * @param notReceiveDTO
     * @return
     */
    @Override
    public R getNotReceivedAssetList(NotReceivedQueryDTO notReceiveDTO) {
        List<NotReceivedVO> notReceivedAssetList = inventoryMapper.getNotReceivedAssetList(notReceiveDTO);
        return R.ok(notReceivedAssetList);
    }

    /**
     * 资产入库操作
     * @param assetWarehousingDTO
     * @return
     */
    @Override
    public R assetWarehousing(AssetWarehousingDTO assetWarehousingDTO) {
        inventoryMapper.assetWarehousing(assetWarehousingDTO);
        return R.ok("入库成功");
    }
}

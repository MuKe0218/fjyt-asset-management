package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DTO.AssetWarehousingDTO;
import com.fjyt.asset.management.POJO.DTO.NotReceivedQueryDTO;
import com.fjyt.asset.management.POJO.DTO.ReceivedQueryDTO;
import com.fjyt.asset.management.POJO.R;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author keQiLong
 * @date 2023年05月18日 10:14
 */
public interface InventoryService {
    /**
     * 获取已入库的资产列表
     * @param receivedDTO
     * @return
     */
     R getReceivedAssetList(ReceivedQueryDTO receivedDTO);

    /**
     * 获取未入库的资产列表
     * @param notReceiveDTO
     * @return
     */
     R getNotReceivedAssetList(NotReceivedQueryDTO notReceiveDTO);

    /**
     * 资产入库操作
     * @param assetWarehousingDTO
     * @return
     */
     R assetWarehousing(@RequestBody AssetWarehousingDTO assetWarehousingDTO);
}

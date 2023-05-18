package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.AssetWarehousingDTO;
import com.fjyt.asset.management.POJO.DTO.NotReceivedQueryDTO;
import com.fjyt.asset.management.POJO.DTO.ReceivedQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author keQiLong
 * @date 2023年05月18日 9:55
 * 库存controller
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * 获取已入库的资产列表
     * @param receivedDTO
     * @return
     */
    @GetMapping("/received")
    public R getReceivedAssetList(ReceivedQueryDTO receivedDTO){
        return inventoryService.getReceivedAssetList(receivedDTO);
    }

    /**
     * 获取未入库的资产列表
     * @param notReceiveDTO
     * @return
     */
    @GetMapping("/notReceived")
    public R getNotReceivedAssetList(NotReceivedQueryDTO notReceiveDTO){
        return inventoryService.getNotReceivedAssetList(notReceiveDTO);
    }

    /**
     * 入库操作
     * @param assetWarehousingDTO
     * @return
     */
    @PutMapping
    public R assetWarehousing(@RequestBody AssetWarehousingDTO assetWarehousingDTO){
        return  inventoryService.assetWarehousing(assetWarehousingDTO);
    }

}

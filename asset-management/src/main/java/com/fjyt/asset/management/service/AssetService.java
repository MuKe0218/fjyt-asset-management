package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetVO;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:06
 * 资产信息
 */
public interface AssetService {
    /**
     * 查询资产信息列表
     * @param assetDTO
     * @return
     */
    public List<AssetVO> list(AssetDTO assetDTO);

    /**
     * 新增资产
     * @param asset
     * @return
     */
    public R add(Asset asset);

}

package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.DTO.AssetQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:06
 * 资产信息
 */
public interface AssetService {
    /**
     * 查询资产信息列表
     * @param assetQueryDTO
     * @return
     */
    public List<AssetVO> list(AssetQueryDTO assetQueryDTO);

    /**
     * 根据id获取资产详情
     * @param id
     * @return
     */
    public R getAssetById(Long id);

    /**
     * 新增资产
     * @param assetDTO
     * @return
     */
    public R add(AssetDTO assetDTO);

    /**
     * 修改资产
     * @param assetDTO
     * @return
     */
    public R update(AssetDTO assetDTO);

    /**
     * 根据assetId删除资产
     * @param id
     * @return
     */
    public R delete(Long id);

}

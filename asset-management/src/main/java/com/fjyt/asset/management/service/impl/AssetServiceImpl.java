package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.service.AssetService;
import com.fjyt.asset.management.POJO.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:07
 * 资产信息
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;
    /**
     * 查询资产信息列表
     * @param assetDTO
     * @return
     */
    public List<AssetVO> list(AssetDTO assetDTO) {
        return assetMapper.list(assetDTO);
    }
    /**
     * 新增资产
     * @param asset
     * @return
     */
    public R add(Asset asset) {
        return null;
    }
}

package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:07
 */
@Mapper
public interface AssetMapper {
    /**
     * 查询资产列表
     * @param assetDTO
     * @return
     */
    public List<AssetVO> list(AssetDTO assetDTO);
}

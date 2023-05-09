package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.AssetPicture;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据id查询资产详情
     * @return
     */
    public AssetVO assetById(Long id);

    /**
     * 新增资产
     * @param asset
     */
    public void add(Asset asset);

    /**
     * 修改资产
     * @param asset
     */
    public void update(Asset asset);

    /**
     * 删除资产
     * @param id
     */
    public void delete(Long id);

    /**
     * 保存资产图片路径关联
     * @param assetPicture
     */
    public void saveAssetPicturePath(AssetPicture assetPicture);
    /**
     * 根据assetCode查询图片路径
     */
    public String getAssetPicturePath(String assetCode);
    /**
     * 删除资产图片关联
     */
    public void delAssetPicturePath(String assetCode);
}

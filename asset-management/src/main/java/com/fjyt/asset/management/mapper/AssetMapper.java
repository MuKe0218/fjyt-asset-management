package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.AssetPicture;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.DTO.AssetQueryDTO;
import com.fjyt.asset.management.POJO.DTO.UpdateStatus;
import com.fjyt.asset.management.POJO.VO.AssetDetailVO;
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
     * @param assetQueryDTO
     * @return
     */
    public List<AssetVO> list(AssetQueryDTO assetQueryDTO);

    /**
     * 根据id查询资产详情
     * @return
     */
    public AssetDetailVO assetById(Long id);

    /**
     * 获取资产名称list
     * @param strings
     * @return
     */
    public List<String> getAssetNameList(List<String> strings);

    /**
     * 根据codes查询list
     * @param list
     * @return
     */
    public List<AssetVO> getAssetByCodes(List<String> list);

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
     * 批量修改资产状态
     */
    public void updateStatusList(UpdateStatus updateStatus);

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

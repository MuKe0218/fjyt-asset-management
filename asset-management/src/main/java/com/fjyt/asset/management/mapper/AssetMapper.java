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
     List<AssetVO> list(AssetQueryDTO assetQueryDTO);

    /**
     * 根据id查询资产详情
     * @return
     */
     AssetDetailVO assetById(Long id);

    /**
     * 获取资产名称list
     * @param strings
     * @return
     */
     List<String> getAssetNameList(@Param("strings") List<String> strings);

    /**
     * 根据codes查询list
     * @param list
     * @return
     */
     List<AssetVO> getAssetByCodes(@Param("list") List<String> list);

    /**
     * 新增资产
     * @param asset
     */
     void add(Asset asset);

    /**
     * 修改资产
     * @param asset
     */
     void update(Asset asset);

    /**
     * 批量修改资产状态
     */
     void updateStatusList(UpdateStatus updateStatus);

    /**
     * 删除资产
     * @param id
     */
     void delete(Long id);

    /**
     * 保存资产图片路径关联
     * @param assetPicture
     */
     void saveAssetPicturePath(AssetPicture assetPicture);
    /**
     * 根据assetCode查询图片路径
     */
     String getAssetPicturePath(String assetCode);
    /**
     * 删除资产图片关联
     */
     void delAssetPicturePath(String assetCode);

    /**
     * 根据 资产名称和限制长度进行查询
     * @param assetName
     * @param limitNum
     * @return
     */
     List<String> getAssetCodeByAssetName(@Param("assetName") String assetName,@Param("limitNum") int limitNum);

    /**
     * 出库 将仓库id设为null
     */
    void updateWarehouse(@Param("codeList") List<String> codeList);

    /**
     * 根据assetCode获取仓库id
     */
    Integer getWarehousing(@Param("assetCode") String assetCode);
}

package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.AssetClassify;
import com.fjyt.asset.management.POJO.DTO.AssetClassifyDTO;
import com.fjyt.asset.management.POJO.VO.AssetClassifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月04日 10:30
 * 分类mapper
 */
@Mapper
public interface AssetClassifyMapper {
    /**
     * 查询分类列表
     * @param assetClassify
     * @return
     */
     List<AssetClassifyVO> assetClassifyList(AssetClassify assetClassify);
    /**
     * 校验分类是否已存在
     * @param classifyName
     * @return
     */
     AssetClassify checkAssetClassifyByName(@Param("classifyName") String classifyName);

    /**
     * 获取祖籍字段
     * @param classifyId
     * @return
     */
     String getAncestorsByClassifyId(@Param("classifyId") Long classifyId);

    /**
     * 根据分类id获取分类详情
     * @param classifyId
     * @return
     */
     AssetClassifyVO assetClassifyByClassifyId(@Param("classifyId") Long classifyId);

    /**
     * 新增分类
     * @param assetClassify
     * @return
     */
     int assetClassifyAdd(AssetClassify assetClassify);

    /**
     * 编辑分类
     * @param assetClassify
     */
     void assetClassifyUpdate(AssetClassify assetClassify);


    /**
     * 删除分类
     * @param classifyId
     */
     void assetClassifyDel(@Param("classifyId") Long classifyId);
}

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
    public List<AssetClassifyVO> assetClassifyList(AssetClassify assetClassify);

    /**
     * 新增分类
     * @param assetClassify
     * @return
     */
    public int assetClassifyAdd(AssetClassify assetClassify);

    /**
     * 校验分类是否已存在
     * @param classifyName
     * @return
     */
    public AssetClassify checkAssetClassifyByName(@Param("classifyName") String classifyName);

    /**
     * 获取祖籍字段
     * @param classifyId
     * @return
     */
    public String getAncestorsByClassifyId(@Param("classifyId") Long classifyId);

    /**
     * 删除分类
     * @param classifyId
     */
    public void assetClassifyDel(@Param("classifyId") Long classifyId);
}

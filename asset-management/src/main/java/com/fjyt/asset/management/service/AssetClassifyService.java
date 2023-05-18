package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DO.AssetClassify;
import com.fjyt.asset.management.POJO.DTO.AssetClassifyDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetClassifyVO;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月04日 10:31
 * 分类业务层
 */
public interface AssetClassifyService {
    /**
     * 查询分类列表
     * @param assetClassify
     * @return
     */
     List<AssetClassifyVO> assetClassifyList(AssetClassify assetClassify);

    /**
     * 根据分类id获取分类详情
     * @param classifyId
     * @return
     */
     R assetClassifyByClassifyId(Long classifyId);

    /**
     * 校验分类是否已存在
     * @param classifyName
     * @return
     */
     AssetClassify checkAssetClassifyByName(String classifyName);

    /**
     * 新增分类
     * @param assetClassifyDTO
     * @return
     */
     R assetClassifyAdd(AssetClassifyDTO assetClassifyDTO);

    /**
     * 编辑分类信息
     * @param assetClassifyDTO
     * @return
     */
     R assetClassifyUpdate(AssetClassifyDTO assetClassifyDTO);

    /**
     * 删除分类
     * @param classifyId
     * @return
     */
     R assetClassifyDel(Long classifyId);
}

package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DTO.AssetUseDTO;
import com.fjyt.asset.management.POJO.DTO.UseQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetUseVO;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月09日 17:30
 * 领用业务层
 */
public interface AssetUseService {
    /**
     * 查询领用单列表
     * @param useQueryDTO
     * @return
     */
     List<AssetUseVO> useList (UseQueryDTO useQueryDTO);

    /**
     * 根据id查询领用单详情
     * @param id
     * @return
     */
     R useListById(Long id);

    /**
     * 新增领用
     * @param assetUseDTO
     * @return
     */
     R addUse(AssetUseDTO assetUseDTO);

    /**
     * 修改领用单
     * @param assetUseDTO
     * @return
     */
     R updateUse(AssetUseDTO assetUseDTO);

    /**
     * 根据id删除领用
     * @param id
     * @return
     */
     R deleteUseById(Long id);

    /**
     * 关联钉钉领用资产
     * @param processInstanceId
     */
    void addUseFromDingDing(List<String> assetCodes,String useUser,String processInstanceId);

    /**
     * 修改领用状态
     * @param status
     */
    void updateStatus(String status,String processInstanceId);

    /**
     * 获取资产codes
     * @param useCode
     * @return
     */
    String getCodes(String useCode);
}

package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.AssetUse;
import com.fjyt.asset.management.POJO.DTO.UseQueryDTO;
import com.fjyt.asset.management.POJO.VO.AssetUseVO;
import com.fjyt.asset.management.POJO.VO.UseDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月09日 17:32
 * 领用接口层
 */
@Mapper
public interface AssetUseMapper {
    /**
     * 查询领用列表
     * @param useQueryDTO
     * @return
     */
     List<AssetUseVO> useList(UseQueryDTO useQueryDTO);

    /**
     * 领用详情
     * @param id
     * @return
     */
     UseDetailVO useListById(Long id);
    /**
     * 新增领用
     * @param assetUse
     */
     void addUse(AssetUse assetUse);

    /**
     * 修改领用
     * @param assetUse
     */
     void updateUse(AssetUse assetUse);
    /**
     * 根据id删除领用
     */
     void deleteUseById(Long id);
}

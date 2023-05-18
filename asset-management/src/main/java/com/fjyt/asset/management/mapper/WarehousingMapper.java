package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.AssetWarehousing;
import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.DTO.WarehousingDTO;
import com.fjyt.asset.management.POJO.DTO.WarehousingQueryDTO;
import com.fjyt.asset.management.POJO.VO.WarehouseDetailVO;
import com.fjyt.asset.management.POJO.VO.WarehousingDetailVO;
import com.fjyt.asset.management.POJO.VO.WarehousingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 16:04
 */
@Mapper
public interface WarehousingMapper {
    /**
     * 获取入库单列表
     * @param warehousingQueryDTO
     * @return
     */
     List<WarehousingVO> getWarehousingList(WarehousingQueryDTO warehousingQueryDTO);

    /**
     * 根据id获取入库单详情
     * @param id
     * @return
     */
     WarehousingDetailVO getWarehousingDetail(Long id);

    /**
     * 新增入库单
     * @param assetWarehousing
     */
    void addWarehousing(AssetWarehousing assetWarehousing);
}

package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.DTO.WarehousingDTO;
import com.fjyt.asset.management.POJO.DTO.WarehousingQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.WarehousingVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @author keQiLong
 * @date 2023年05月18日 16:13
 */
public interface WarehousingService {
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
     R getWarehousingDetail(@PathVariable Long id);

    /**
     * 新增入库单
     * @param warehousingDTO
     */
     void addWarehousing(WarehousingDTO warehousingDTO);
}

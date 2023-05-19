package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DO.Procure;
import com.fjyt.asset.management.POJO.DTO.ProcureQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.ProcureVO;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:57
 */
public interface ProcureService {
    /**
     * 获取采购信息列表
     * @param procureQueryDTO
     * @return
     */
    List<ProcureVO> getProcureList(ProcureQueryDTO procureQueryDTO);

    /**
     * 根据id获取采购信息详情
     * @param id
     * @return
     */
    R getProcureDetail(Long id);

    /**
     * 新增采购信息
     * @param procure
     */
    void addProcure(Procure procure);
}

package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DO.Procure;
import com.fjyt.asset.management.POJO.DTO.ProcureQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.ProcureVO;
import org.springframework.web.bind.annotation.PathVariable;

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

    /**
     * 采购验收
     * @param id
     * @return
     */
    R checkAndAccept(@PathVariable Long id);

    /**
     * 修改状态
     * @param status
     * @param processInstanceId
     */
    void updateStatus(String status,String processInstanceId);

    /**
     * 关联钉钉生成采购单
     */
    void addProcureByDingDing(Procure procure,String processInstanceId);
}

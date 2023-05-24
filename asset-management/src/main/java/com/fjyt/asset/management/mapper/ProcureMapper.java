package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.Procure;
import com.fjyt.asset.management.POJO.DTO.ProcureQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.ProcureDetailVO;
import com.fjyt.asset.management.POJO.VO.ProcureVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:36
 */
@Mapper
public interface ProcureMapper {
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
    ProcureDetailVO getProcureDetail(Long id);

    /**
     * 修改采购单
     * @param procure
     */
    void updateProcure(Procure procure);

    /**
     * 新增采购信息
     * @param procure
     */
    void addProcure(Procure procure);

    /**
     * 修改状态
     * @param status
     * @param procureCode
     */
    void updateStatus(@Param("status") String status,@Param("procureCode") String procureCode);
}
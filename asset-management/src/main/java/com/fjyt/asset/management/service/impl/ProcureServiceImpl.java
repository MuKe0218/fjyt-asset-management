package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.Procure;
import com.fjyt.asset.management.POJO.DTO.ProcureQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.ProcureVO;
import com.fjyt.asset.management.mapper.ProcureMapper;
import com.fjyt.asset.management.service.ProcureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:57
 */
@Service
public class ProcureServiceImpl implements ProcureService {

    @Autowired
    private ProcureMapper procureMapper;

    /**
     * 查询采购信息列表
     * @param procureQueryDTO
     * @return
     */
    @Override
    public List<ProcureVO> getProcureList(ProcureQueryDTO procureQueryDTO) {
        return procureMapper.getProcureList(procureQueryDTO);
    }

    /**
     * 根据id查询采购信息详情
     * @param id
     * @return
     */
    @Override
    public R getProcureDetail(Long id) {
        return R.ok(procureMapper.getProcureDetail(id));
    }

    /**
     * 新增采购信息
     * @param procure
     */
    @Override
    public void addProcure(Procure procure) {
        procureMapper.addProcure(procure);
    }
}

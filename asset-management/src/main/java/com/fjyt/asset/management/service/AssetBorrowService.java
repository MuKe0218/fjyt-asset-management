package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DTO.AssetBorrowDTO;
import com.fjyt.asset.management.POJO.DTO.BorrowQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetBorrowVO;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月11日 14:45
 */
public interface AssetBorrowService {
    /**
     * 查询借用列表
     * @param borrowQueryDTO
     * @return
     */
     List<AssetBorrowVO> borrowList(BorrowQueryDTO borrowQueryDTO);

    /**
     * 根据id查询借用单
     * @param id
     * @return
     */
     R getBorrowById(Long id);

    /**
     * 新增借用单
     * @param assetBorrowDTO
     * @return
     */
     R borrowAdd(AssetBorrowDTO assetBorrowDTO);

    /**
     * 编辑借用单
     * @param assetBorrowDTO
     * @return
     */
     R borrowUpdate(AssetBorrowDTO assetBorrowDTO);

    /**
     * 根据id删除借用单
     * @param id
     * @return
     */
     R borrowDelete(Long id);

    /**
     * 关联钉钉领用资产
     * @param processInstanceId
     */
    void addBorrowFromDingDing(List<String> assetCode,String borrowUser,String processInstanceId);

    /**
     * 修改状态
     */
    void updateStatus(String status,String processInstanceId);
    /**
     * 获取资产codes
     * @param borrowCode
     * @return
     */
    String getCodes(String borrowCode);
}

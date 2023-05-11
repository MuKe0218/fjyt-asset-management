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
    public List<AssetBorrowVO> borrowList(BorrowQueryDTO borrowQueryDTO);

    /**
     * 根据id查询借用单
     * @param id
     * @return
     */
    public R getBorrowById(Long id);

    /**
     * 新增借用单
     * @param assetBorrowDTO
     * @return
     */
    public R borrowAdd(AssetBorrowDTO assetBorrowDTO);

    /**
     * 编辑借用单
     * @param assetBorrowDTO
     * @return
     */
    public R borrowUpdate(AssetBorrowDTO assetBorrowDTO);

    /**
     * 根据id删除借用单
     * @param id
     * @return
     */
    public R borrowDelete(Long id);
}

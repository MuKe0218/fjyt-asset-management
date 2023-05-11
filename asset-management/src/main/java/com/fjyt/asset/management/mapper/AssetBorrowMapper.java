package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.AssetBorrow;
import com.fjyt.asset.management.POJO.DTO.BorrowQueryDTO;
import com.fjyt.asset.management.POJO.VO.AssetBorrowVO;
import com.fjyt.asset.management.POJO.VO.BorrowDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月11日 14:46
 */
@Mapper
public interface AssetBorrowMapper {
    /**
     * 查询借用单列表
     * @param borrowQueryDTO
     * @return
     */
    public List<AssetBorrowVO> borrowList(BorrowQueryDTO borrowQueryDTO);

    /**
     * 根据id查询借用单详情
     * @param id
     * @return
     */
    public BorrowDetailVO getBorrowById(Long id);

    /**
     * 新增借用单
     * @param assetBorrow
     */
    public void borrowAdd(AssetBorrow assetBorrow);

    /**
     * 编辑领用单
     * @param assetBorrow
     */
    public void borrowUpdate(AssetBorrow assetBorrow);

    /**
     * 根据id删除借用单
     * @param id
     */
    public void borrowDelete(Long id);
}

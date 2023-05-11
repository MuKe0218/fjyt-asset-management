package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DTO.AssetBorrowDTO;
import com.fjyt.asset.management.POJO.DTO.BorrowQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.TableDataInfo;
import com.fjyt.asset.management.POJO.VO.AssetBorrowVO;
import com.fjyt.asset.management.service.AssetBorrowService;
import com.fjyt.asset.management.utils.AssetUtils;
import com.fjyt.asset.management.utils.PageHelperUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月17日 15:03
 */
@RestController
@RequestMapping("/assetBorrow")
public class AssetBorrowController {

    @Autowired
    private AssetBorrowService assetBorrowService;

    /**
     * 查询借用列表
     * @param borrowQueryDTO
     * @return
     */
    @GetMapping
    public TableDataInfo borrowList( BorrowQueryDTO borrowQueryDTO){
        PageHelper.startPage(borrowQueryDTO.getPageNum(),borrowQueryDTO.getPageSize());
        List<AssetBorrowVO> assetBorrowVOS = assetBorrowService.borrowList(borrowQueryDTO);
        return new PageHelperUtil().resultInfo(assetBorrowVOS);
    }

    /**
     * 根据id查询借用单
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getBorrowById(@PathVariable Long id){
        return assetBorrowService.getBorrowById(id);
    }
    /**
     * 查询状态列表
     * @return
     */
    @GetMapping("/status")
    public R borrowStatusList(){
        return R.ok(AssetUtils.borrowStatusList());
    }

    /**
     * 新增借用单
     * @param assetBorrowDTO
     * @return
     */
    @PostMapping
    public R borrowAdd(@RequestBody AssetBorrowDTO assetBorrowDTO){
        return assetBorrowService.borrowAdd(assetBorrowDTO);
    }

    /**
     * 编辑借用单
     * @param assetBorrowDTO
     * @return
     */
    @PutMapping
    public R borrowUpdate(@RequestBody AssetBorrowDTO assetBorrowDTO){
        return assetBorrowService.borrowUpdate(assetBorrowDTO);
    }

    /**
     * 根据id删除借用单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R borrowDelete(@PathVariable Long id){
        return assetBorrowService.borrowDelete(id);
    }
}

package com.fjyt.asset.management.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.AssetBorrow;
import com.fjyt.asset.management.POJO.DTO.AssetBorrowDTO;
import com.fjyt.asset.management.POJO.DTO.BorrowQueryDTO;
import com.fjyt.asset.management.POJO.DTO.UpdateStatus;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetBorrowVO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.POJO.VO.BorrowDetailVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetBorrowMapper;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.service.AssetBorrowService;
import com.fjyt.asset.management.utils.DateUtils;
import com.fjyt.asset.management.utils.JwtUtils;
import com.fjyt.asset.management.utils.SerialNumberUtils;
import com.fjyt.asset.management.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author keQiLong
 * @date 2023年05月11日 14:45
 */
@Service
public class AssetBorrowServiceImpl implements AssetBorrowService {


    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private AssetBorrowMapper assetBorrowMapper;
    /**
     * 查询借用列表
     * @param borrowQueryDTO
     * @return
     */
    @Override
    public List<AssetBorrowVO> borrowList(BorrowQueryDTO borrowQueryDTO) {
        List<AssetBorrowVO> assetBorrowVOS = assetBorrowMapper.borrowList(borrowQueryDTO);
        assetBorrowVOS.forEach(assetBorrowVO -> {
            List<String> strings = Arrays.asList(assetBorrowVO.getAssetCodes().replace(" ","").split(","));
            List<String> assetNameList = assetMapper.getAssetNameList(strings);
            String assetNames = assetNameList.toString();
            assetBorrowVO.setAssetNames(assetNames.substring(1,assetNames.length()-1));
        });
        return assetBorrowVOS;
    }
    /**
     * 根据id查询借用单
     * @param id
     * @return
     */
    @Override
    public R getBorrowById(Long id) {
        BorrowDetailVO borrowDetailVO = assetBorrowMapper.getBorrowById(id);
        List<String> strings = Arrays.asList(borrowDetailVO.getAssetCodes().replace(" ","").split(","));
        List<AssetVO> assetByCodes = assetMapper.getAssetByCodes(strings);
        borrowDetailVO.setAssetSelectList(assetByCodes);
        return R.ok(borrowDetailVO);
    }
    /**
     * 新增借用单
     * @param assetBorrowDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R borrowAdd(AssetBorrowDTO assetBorrowDTO) {
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetBorrowDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        // 批量修改资产状态
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("2");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);

        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetBorrow assetBorrow = new AssetBorrow();
        assetBorrow.setBorrowCode(new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_JY));
        assetBorrow.setBorrowUser(assetBorrowDTO.getBorrowUser());
        assetBorrow.setBorrowTime(assetBorrowDTO.getBorrowTime());
        assetBorrow.setBorrowStartTime(assetBorrowDTO.getBorrowStartTime());
        assetBorrow.setExpectedReturnTime(assetBorrowDTO.getExpectedReturnTime());
        assetBorrow.setRemark(assetBorrowDTO.getRemark());
        assetBorrow.setCreateUser(userName);
        assetBorrow.setCreateTime(DateUtils.getNowDate());
        assetBorrow.setUpdateUser(userName);
        assetBorrow.setUpdateTime(DateUtils.getNowDate());
        assetBorrow.setAssetCodes(collect.toString().substring(1,collect.toString().length()-1));
        assetBorrowMapper.borrowAdd(assetBorrow);

        return R.ok("新增成功");
    }
    /**
     * 编辑借用单
     * @param assetBorrowDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R borrowUpdate(AssetBorrowDTO assetBorrowDTO) {
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetBorrowDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        // 查询原来的
        List<String> strings = Arrays.asList(assetBorrowMapper.getBorrowById(assetBorrowDTO.getId()).getAssetCodes().replace(" ","").split(","));
        UpdateStatus updateStatus = new UpdateStatus();
        //先修改原有资产状态
        updateStatus.setStatus("0");
        updateStatus.setCollect(strings);
        assetMapper.updateStatusList(updateStatus);
        // 再批量修改资产状态
        updateStatus.setStatus("1");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);

        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetBorrow assetBorrow = new AssetBorrow();
        assetBorrow.setBorrowUser(assetBorrowDTO.getBorrowUser());
        assetBorrow.setBorrowTime(assetBorrowDTO.getBorrowTime());
        assetBorrow.setBorrowStartTime(assetBorrowDTO.getBorrowStartTime());
        assetBorrow.setExpectedReturnTime(assetBorrowDTO.getExpectedReturnTime());
        assetBorrow.setRemark(assetBorrowDTO.getRemark());
        assetBorrow.setUpdateUser(userName);
        assetBorrow.setUpdateTime(DateUtils.getNowDate());
        assetBorrow.setAssetCodes(collect.toString().substring(1,collect.toString().length()-1));
        assetBorrowMapper.borrowUpdate(assetBorrow);
        return R.ok("编辑成功");
    }
    /**
     * 根据id删除借用单
     * @param id
     * @return
     */
    @Override
    public R borrowDelete(Long id) {
        List<String> strings = Arrays.asList(assetBorrowMapper.getBorrowById(id).getAssetCodes().replace(" ","").split(","));
        UpdateStatus updateStatus = new UpdateStatus();
        //先修改原有资产状态
        updateStatus.setStatus("0");
        updateStatus.setCollect(strings);
        assetMapper.updateStatusList(updateStatus);
        assetBorrowMapper.borrowDelete(id);
        return R.ok("删除成功");
    }
}

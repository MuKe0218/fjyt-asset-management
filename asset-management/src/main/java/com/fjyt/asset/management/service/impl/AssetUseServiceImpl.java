package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.AssetUse;
import com.fjyt.asset.management.POJO.DTO.AssetUseDTO;
import com.fjyt.asset.management.POJO.DTO.UpdateStatus;
import com.fjyt.asset.management.POJO.DTO.UseQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetUseVO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.POJO.VO.UseDetailVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.mapper.AssetUseMapper;
import com.fjyt.asset.management.service.AssetUseService;
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
 * @date 2023年05月09日 17:31
 * 领用业务实现层
 */
@Service
public class AssetUseServiceImpl implements AssetUseService {

    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private AssetUseMapper assetUseMapper;

    /**
     * 查询领用列表
     * @param useQueryDTO
     * @return
     */
    @Override
    public R useList(UseQueryDTO useQueryDTO) {
        List<AssetUseVO> assetUseVOS = assetUseMapper.useList(useQueryDTO);
        assetUseVOS.stream().forEach(assetUseVO -> {
            List<String> strings = Arrays.asList(assetUseVO.getAssetCodes());
            List<String> assetNameList = assetMapper.getAssetNameList(strings);
            assetUseVO.setAssetNames(assetNameList.toString());
        });
        return R.ok(assetUseVOS);
    }

    /**
     * 根据id查询领用详情
     * @param id
     * @return
     */
    @Override
    public R useListById(Long id) {
        UseDetailVO useDetailVO = assetUseMapper.useListById(id);
        List<String> strings = Arrays.asList(useDetailVO.getAssetCodes());
        List<AssetVO> assetByCodes = assetMapper.getAssetByCodes(strings);
        useDetailVO.setAssetSelectList(assetByCodes);
        return R.ok(useDetailVO);
    }

    /**
     * 新增领用
     * @param assetUseDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R addUse(AssetUseDTO assetUseDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        System.out.println(assetUseDTO);
        AssetUse assetUse = new AssetUse();
        assetUse.setUseCode(new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_LY));
        assetUse.setUseUser(assetUseDTO.getUseUser());
        assetUse.setUseTime(assetUseDTO.getUseTime());
        assetUse.setRemark(assetUseDTO.getRemark());
        assetUse.setCreateUser(userName);
        assetUse.setCreateTime(DateUtils.getNowDate());
        assetUse.setUpdateUser(userName);
        assetUse.setUpdateTime(DateUtils.getNowDate());
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetUseDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        assetUse.setAssetCodes(collect.toString());
        // 新增领用
        assetUseMapper.addUse(assetUse);
        // 批量修改资产状态
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("1");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);
        return R.ok("新增领用成功");
    }

    /**
     * 修改领用
     * @param assetUseDTO
     * @return
     */
    @Override
    public R updateUse(AssetUseDTO assetUseDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetUse assetUse = new AssetUse();
        assetUse.setUseUser(assetUseDTO.getUseUser());
        assetUse.setUseTime(assetUseDTO.getUseTime());
        assetUse.setRemark(assetUseDTO.getRemark());
        assetUse.setUpdateUser(userName);
        assetUse.setUpdateTime(DateUtils.getNowDate());
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetUseDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        assetUse.setAssetCodes(collect.toString());
        // 新增领用
        assetUseMapper.addUse(assetUse);
        // 查询原来的
        List<String> strings = Arrays.asList(assetUseMapper.useListById(assetUseDTO.getId()).getAssetCodes());
        UpdateStatus updateStatus = new UpdateStatus();
        //先修改原有资产状态
        updateStatus.setStatus("0");
        updateStatus.setCollect(strings);
        assetMapper.updateStatusList(updateStatus);
        // 再批量修改资产状态
        updateStatus.setStatus("1");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);

        return R.ok("修改成功");
    }

    /**
     * 根据id删除领用
     * @param id
     * @return
     */
    @Override
    public R deleteUseById(Long id) {
        return null;
    }
}

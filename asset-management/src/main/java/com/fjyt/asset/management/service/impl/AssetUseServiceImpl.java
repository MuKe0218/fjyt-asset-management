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

import java.util.ArrayList;
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
    public  List<AssetUseVO> useList(UseQueryDTO useQueryDTO) {
        List<AssetUseVO> assetUseVOS = assetUseMapper.useList(useQueryDTO);
        assetUseVOS.forEach(assetUseVO -> {
            List<String> strings = Arrays.asList(assetUseVO.getAssetCodes().replace(" ","").split(","));
            List<String> assetNameList = assetMapper.getAssetNameList(strings);
            String assetNames = assetNameList.toString();
            assetUseVO.setAssetNames(assetNames.substring(1,assetNames.length()-1));
        });
        return assetUseVOS;
    }

    /**
     * 根据id查询领用详情
     * @param id
     * @return
     */
    @Override
    public R useListById(Long id) {
        UseDetailVO useDetailVO = assetUseMapper.useListById(id);
        List<String> strings = Arrays.asList(useDetailVO.getAssetCodes().replace(" ","").split(","));
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
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetUseDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        // 批量修改资产状态
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("1");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);

        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetUse assetUse = new AssetUse();
        assetUse.setUseCode(new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_LY));
        assetUse.setUseUser(assetUseDTO.getUseUser());
        assetUse.setUseTime(assetUseDTO.getUseTime());
        assetUse.setRemark(assetUseDTO.getRemark());
        assetUse.setCreateUser(userName);
        assetUse.setCreateTime(DateUtils.getNowDate());
        assetUse.setUpdateUser(userName);
        assetUse.setUpdateTime(DateUtils.getNowDate());
        assetUse.setAssetCodes(collect.toString().substring(1,collect.toString().length()-1));
        // 新增领用
        assetUseMapper.addUse(assetUse);
        return R.ok("新增领用成功");
    }

    /**
     * 修改领用
     * @param assetUseDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R updateUse(AssetUseDTO assetUseDTO) {
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetUseDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        // 查询原来的
        List<String> strings = Arrays.asList(assetUseMapper.useListById(assetUseDTO.getId()).getAssetCodes().replace(" ","").split(","));
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
        AssetUse assetUse = new AssetUse();
        assetUse.setId(assetUseDTO.getId());
        assetUse.setUseUser(assetUseDTO.getUseUser());
        assetUse.setUseTime(assetUseDTO.getUseTime());
        assetUse.setRemark(assetUseDTO.getRemark());
        assetUse.setUpdateUser(userName);
        assetUse.setUpdateTime(DateUtils.getNowDate());
        assetUse.setAssetCodes(collect.toString().substring(1,collect.toString().length()-1));
        // 修改领用
        assetUseMapper.updateUse(assetUse);
        return R.ok("修改成功");
    }

    /**
     * 根据id删除领用
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R deleteUseById(Long id) {
        List<String> strings = Arrays.asList(assetUseMapper.useListById(id).getAssetCodes().replace(" ","").split(","));
        UpdateStatus updateStatus = new UpdateStatus();
        //先修改原有资产状态
        updateStatus.setStatus("0");
        updateStatus.setCollect(strings);
        assetMapper.updateStatusList(updateStatus);
        // 删除领用
        assetUseMapper.deleteUseById(id);
        return R.ok("删除成功");
    }
}

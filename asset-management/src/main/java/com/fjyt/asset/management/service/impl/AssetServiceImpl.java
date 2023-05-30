package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DTO.AssetDTO;
import com.fjyt.asset.management.POJO.DTO.AssetQueryDTO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetClassifyMapper;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.service.AssetService;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年04月27日 15:07
 * 资产信息
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private AssetClassifyMapper assetClassifyMapper;
    /**
     * 查询资产信息列表
     * @param assetQueryDTO
     * @return
     */
    public List<AssetVO> list(AssetQueryDTO assetQueryDTO) {
        return assetMapper.list(assetQueryDTO);
    }

    /**
     * 根据id获取资产详情
     * @param id
     * @return
     */
    @Override
    public R getAssetById(Long id) {
        return R.ok(assetMapper.assetById(id));
    }

    /**
     * 新增资产
     * @param assetDTO
     * @return
     */
    public R add(AssetDTO assetDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        Asset asset = new Asset();
        String assetCode = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_ZC);
        asset.setAssetCode(assetCode);
        asset.setAssetName(assetDTO.getAssetName());
        asset.setAssetSpecifications(assetDTO.getAssetSpecifications());
        asset.setAssetUnit(assetDTO.getAssetUnit());
        asset.setClassifyId(assetDTO.getClassifyId());
        asset.setAssetPrice(assetDTO.getAssetPrice());
        asset.setRemark(assetDTO.getRemark());
        asset.setCreateUser(userName);
        asset.setCreateTime(DateUtils.getNowDate());
        asset.setUpdateUser(userName);
        asset.setUpdateTime(DateUtils.getNowDate());
        assetMapper.add(asset);
        return R.ok(assetCode,"新增成功");
    }

    /**
     * 修改资产
     * @param assetDTO
     * @return
     */
    @Override
    public R update(AssetDTO assetDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        Asset asset = new Asset();
        asset.setId(assetDTO.getId());
        asset.setAssetName(assetDTO.getAssetName());
        asset.setStatus(assetDTO.getStatus());
        asset.setAssetSpecifications(assetDTO.getAssetSpecifications());
        asset.setAssetUnit(assetDTO.getAssetUnit());
        asset.setClassifyId(assetDTO.getClassifyId());
        asset.setAssetPrice(assetDTO.getAssetPrice());
        asset.setRemark(assetDTO.getRemark());
        asset.setUpdateUser(userName);
        asset.setUpdateTime(DateUtils.getNowDate());
        assetMapper.update(asset);
        return R.ok("修改成功");
    }

    /**
     * 根据assetId删除资产
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R delete(Long id) {
        // 删除资产对应图片
        // 1.根据id获取assetCode
        String assetCode = assetMapper.assetById(id).getAssetCode();
        // 2.根据assetCode获取图片的存储位置
        String assetPicturePath = assetMapper.getAssetPicturePath(assetCode);
        // 3.删除资产对应图片
        if (StringUtils.isNotNull(assetPicturePath)){
            File file = new File(assetPicturePath);
            file.delete();
        }
        // 删除资产
        assetMapper.delete(id);
        return R.ok("删除成功");
    }
}

package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.AssetMaintenance;
import com.fjyt.asset.management.POJO.DTO.AssetMaintenanceDTO;
import com.fjyt.asset.management.POJO.DTO.MaintenanceQueryDTO;
import com.fjyt.asset.management.POJO.DTO.UpdateStatus;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetMaintenanceVO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.POJO.VO.MaintenanceDetailVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetMaintenanceMapper;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.service.AssetMaintenanceService;
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
 * @date 2023年05月12日 11:27
 */
@Service
public class AssetMaintenanceServiceImpl implements AssetMaintenanceService {

    @Autowired
    private AssetMaintenanceMapper assetMaintenanceMapper;
    @Autowired
    private AssetMapper assetMapper;
    /**
     * 查询维修信息列表
     * @param maintenanceQueryDTO
     * @return
     */
    @Override
    public List<AssetMaintenanceVO> maintenanceList(MaintenanceQueryDTO maintenanceQueryDTO) {
        List<AssetMaintenanceVO> assetMaintenanceVOS = assetMaintenanceMapper.maintenanceList(maintenanceQueryDTO);
        assetMaintenanceVOS.forEach(assetMaintenanceVO -> {
            List<String> strings = Arrays.asList(assetMaintenanceVO.getAssetCodes().replace(" ","").split(","));
            List<String> assetNameList = assetMapper.getAssetNameList(strings);
            String assetNames = assetNameList.toString();
            assetMaintenanceVO.setAssetNames(assetNames.substring(1,assetNames.length()-1));
        });
        return assetMaintenanceVOS;
    }

    /**
     * 根据id查询维修信息
     * @param id
     * @return
     */
    @Override
    public R getMaintenanceById(Long id) {
        MaintenanceDetailVO maintenanceDetailVO = assetMaintenanceMapper.getMaintenanceById(id);
        List<String> strings = Arrays.asList(maintenanceDetailVO.getAssetCodes().replace(" ","").split(","));
        List<AssetVO> assetByCodes = assetMapper.getAssetByCodes(strings);
        maintenanceDetailVO.setAssetSelectList(assetByCodes);
        return R.ok(maintenanceDetailVO);
    }

    /**
     * 新增维修信息
     * @param assetMaintenanceDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R addMaintenance(AssetMaintenanceDTO assetMaintenanceDTO) {
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetMaintenanceDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        // 批量修改资产状态
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("4");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);

        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetMaintenance assetMaintenance = new AssetMaintenance();
        assetMaintenance.setMaintenanceCode(new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_WX));
        assetMaintenance.setAssetCodes(collect.toString().substring(1,collect.toString().length()-1));
        assetMaintenance.setMaintenanceUser(userName);
        assetMaintenance.setMaintenanceTime(DateUtils.getNowDate());
        assetMaintenance.setRemark(assetMaintenanceDTO.getRemark());
        assetMaintenance.setCreateUser(userName);
        assetMaintenance.setCreateTime(DateUtils.getNowDate());
        assetMaintenance.setUpdateUser(userName);
        assetMaintenance.setUpdateTime(DateUtils.getNowDate());
        assetMaintenanceMapper.addMaintenance(assetMaintenance);

        return R.ok("新增成功");
    }

    /**
     * 修改为修信息
     * @param assetMaintenanceDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R updateMaintenance(AssetMaintenanceDTO assetMaintenanceDTO) {
        // 取出资产list中的每个assetCode
        List<Asset> assetList = assetMaintenanceDTO.getAssetSelectList();
        List<String> collect = assetList.stream().map(Asset::getAssetCode).collect(Collectors.toList());
        // 查询原来的
        List<String> strings = Arrays.asList(assetMaintenanceMapper.getMaintenanceById(assetMaintenanceDTO.getId()).getAssetCodes().replace(" ","").split(","));
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
        AssetMaintenance assetMaintenance = new AssetMaintenance();
        assetMaintenance.setAssetCodes(collect.toString().substring(1,collect.toString().length()-1));
        assetMaintenance.setMaintenanceUser(userName);
        assetMaintenance.setMaintenanceTime(DateUtils.getNowDate());
        assetMaintenance.setRemark(assetMaintenanceDTO.getRemark());
        assetMaintenance.setUpdateUser(userName);
        assetMaintenance.setUpdateTime(DateUtils.getNowDate());
        assetMaintenanceMapper.updateMaintenance(assetMaintenance);

        return R.ok("修改成功");
    }

    /**
     * 根据id删除维修信息
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R delMaintenance(Long id) {
        List<String> strings = Arrays.asList(assetMaintenanceMapper.getMaintenanceById(id).getAssetCodes().replace(" ","").split(","));
        UpdateStatus updateStatus = new UpdateStatus();
        //先修改原有资产状态
        updateStatus.setStatus("0");
        updateStatus.setCollect(strings);
        assetMapper.updateStatusList(updateStatus);

        assetMaintenanceMapper.delMaintenance(id);
        return R.ok("删除成功");
    }
}

package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.AssetUse;
import com.fjyt.asset.management.POJO.DO.ManagementDingDing;
import com.fjyt.asset.management.POJO.DTO.AssetUseDTO;
import com.fjyt.asset.management.POJO.DTO.OutboundDTO;
import com.fjyt.asset.management.POJO.DTO.UpdateStatus;
import com.fjyt.asset.management.POJO.DTO.UseQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetUseVO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.POJO.VO.UseDetailVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.mapper.AssetUseMapper;
import com.fjyt.asset.management.mapper.DingDingMapper;
import com.fjyt.asset.management.service.AssetUseService;
import com.fjyt.asset.management.service.OutboundService;
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
    @Autowired
    private DingDingMapper dingDingMapper;
    @Autowired
    private OutboundService outboundService;

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
        String assetCodes = collect.toString().substring(1,collect.toString().length()-1);
        // 批量修改资产状态
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("1");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);
        // 出库
        assetMapper.updateWarehouse(collect);
        // 新增出库单
        OutboundDTO outboundDTO = new OutboundDTO();
        outboundDTO.setAssetCodes(assetCodes);
        outboundService.addOutbound(outboundDTO);

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
        assetUse.setAssetCodes(assetCodes);
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

    /**
     * 关联钉钉领用资产
     * @param processInstanceId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUseFromDingDing(List<String> assetCodes,String useUser,String processInstanceId) {
        System.out.println("关联钉钉创建领用单");
        String code = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_LY);
        String assetCodesString = assetCodes.toString().substring(1,assetCodes.toString().length()-1);
        // 新增领用与审批关联表
        ManagementDingDing managementDingDing = new ManagementDingDing();
        managementDingDing.setCode(code);
        managementDingDing.setProcessInstanceId(processInstanceId);
        dingDingMapper.addManagementDingDing(managementDingDing);
        // 新增领用单
        AssetUse assetUse = new AssetUse();
        assetUse.setUseCode(code);
        assetUse.setUseUser(useUser);
        assetUse.setUseTime(DateUtils.getNowDate());
        assetUse.setCreateTime(DateUtils.getNowDate());
        assetUse.setUpdateTime(DateUtils.getNowDate());
        assetUse.setAssetCodes(assetCodesString);
        assetUse.setCreateWay("1");
        assetUse.setStatus("1");
        assetUseMapper.addUse(assetUse);
        // 将所领用的资产状态变为审批中
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("3");
        updateStatus.setCollect(assetCodes);
        assetMapper.updateStatusList(updateStatus);
        // 出库
        assetMapper.updateWarehouse(assetCodes);
        // 创建出库单
        OutboundDTO outboundDTO = new OutboundDTO();
        outboundDTO.setAssetCodes(assetCodesString);
        outboundService.addOutbound(outboundDTO);
    }

    /**
     * 修改领用状态
     * @param status
     */
    @Override
    public void updateStatus(String status,String processInstanceId) {
        // 获取领用单code
        String code = dingDingMapper.getCode(processInstanceId);
        assetUseMapper.updateStatus(status,code);
    }

    /**
     * 获取资产Codes
     * @param useCode
     * @return
     */
    @Override
    public String getCodes(String useCode) {
        return assetUseMapper.getCodes(useCode);
    }
}

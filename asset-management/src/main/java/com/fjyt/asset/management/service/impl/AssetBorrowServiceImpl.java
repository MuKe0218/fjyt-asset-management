package com.fjyt.asset.management.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.AssetBorrow;
import com.fjyt.asset.management.POJO.DO.ManagementDingDing;
import com.fjyt.asset.management.POJO.DTO.AssetBorrowDTO;
import com.fjyt.asset.management.POJO.DTO.BorrowQueryDTO;
import com.fjyt.asset.management.POJO.DTO.OutboundDTO;
import com.fjyt.asset.management.POJO.DTO.UpdateStatus;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetBorrowVO;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.POJO.VO.BorrowDetailVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetBorrowMapper;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.mapper.DingDingMapper;
import com.fjyt.asset.management.service.AssetBorrowService;
import com.fjyt.asset.management.service.OutboundService;
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
    @Autowired
    private DingDingMapper dingDingMapper;
    @Autowired
    private OutboundService outboundService;
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
        String assetCodes = collect.toString().substring(1,collect.toString().length()-1);
        // 批量修改资产状态
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("2");
        updateStatus.setCollect(collect);
        assetMapper.updateStatusList(updateStatus);

        List<String> collect1 = collect.stream().map(assetCode -> {
            Integer warehouseId = assetMapper.getWarehousing(assetCode);
            if (warehouseId != null) {
                return assetCode;
            }
            return "";
        }).filter(string -> !string.isEmpty()).collect(Collectors.toList());
        if (collect1.size() > 0){
            // 出库
            assetMapper.updateWarehouse(collect1);
            // 新增出库单
            OutboundDTO outboundDTO = new OutboundDTO();
            outboundDTO.setAssetCodes(collect1.toString().substring(1,collect1.toString().length()-1));
            outboundService.addOutbound(outboundDTO);
        }

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
        assetBorrow.setAssetCodes(assetCodes);
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
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 关联钉钉新增借用单
     * @param assetCodes
     * @param borrowUser
     * @param processInstanceId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBorrowFromDingDing(List<String> assetCodes, String borrowUser, String processInstanceId) {
        System.out.println("关联钉钉创建借用单");
        String code = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_JY);
        String assetCodesString = assetCodes.toString().substring(1,assetCodes.toString().length()-1);
        // 新增借用与审批关联表
        ManagementDingDing managementDingDing = new ManagementDingDing();
        managementDingDing.setCode(code);
        managementDingDing.setProcessInstanceId(processInstanceId);
        dingDingMapper.addManagementDingDing(managementDingDing);
        // 新增借用单
        AssetBorrow assetBorrow = new AssetBorrow();
        assetBorrow.setBorrowCode(code);
        assetBorrow.setBorrowUser(borrowUser);
        assetBorrow.setBorrowTime(DateUtils.getNowDate());
        assetBorrow.setCreateWay("1");
        assetBorrow.setStatus("1");
        assetBorrow.setCreateTime(DateUtils.getNowDate());
        assetBorrow.setUpdateTime(DateUtils.getNowDate());
        assetBorrow.setAssetCodes(assetCodesString);
        assetBorrowMapper.borrowAdd(assetBorrow);
        // 将所借用的资产状态变为审批中
        UpdateStatus updateStatus = new UpdateStatus();
        updateStatus.setStatus("3");
        updateStatus.setCollect(assetCodes);
        assetMapper.updateStatusList(updateStatus);

        List<String> collect1 = assetCodes.stream().map(assetCode -> {
            Integer warehouseId = assetMapper.getWarehousing(assetCode);
            if (warehouseId != null) {
                return assetCode;
            }
            return "";
        }).filter(string -> !string.isEmpty()).collect(Collectors.toList());
        if (collect1.size() > 0){
            // 出库
            assetMapper.updateWarehouse(collect1);
            // 新增出库单
            OutboundDTO outboundDTO = new OutboundDTO();
            outboundDTO.setAssetCodes(collect1.toString().substring(1,collect1.toString().length()-1));
            outboundService.addOutbound(outboundDTO);
        }
    }

    /**
     * 修改状态
     * @param status
     * @param processInstanceId
     */
    @Override
    public void updateStatus(String status, String processInstanceId) {
        // 获取领用单code
        String code = dingDingMapper.getCode(processInstanceId);
        assetBorrowMapper.updateStatus(status,code);
    }

    /**
     * 根据borrowCode查询资产codes
     * @param borrowCode
     * @return
     */
    @Override
    public String getCodes(String borrowCode) {
        return assetBorrowMapper.getCodes(borrowCode);
    }

    /**
     * 归还
     * @param id
     * @return
     */
    @Override
    public R assetReturn(Long id) {
        // 修改借用状态
        assetBorrowMapper.updateStatusById(id);
        // 修改资产状态
        BorrowDetailVO borrowDetailVO = assetBorrowMapper.getBorrowById(id);
        List<String> strings = Arrays.asList(borrowDetailVO.getAssetCodes().replace(" ","").split(","));
        assetMapper.updateStatusList(new UpdateStatus("0",strings));
        return null;
    }
}

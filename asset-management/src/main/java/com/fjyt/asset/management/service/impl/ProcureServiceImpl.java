package com.fjyt.asset.management.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.fjyt.asset.management.POJO.DO.Asset;
import com.fjyt.asset.management.POJO.DO.ManagementDingDing;
import com.fjyt.asset.management.POJO.DO.Procure;
import com.fjyt.asset.management.POJO.DTO.DingDingDTO;
import com.fjyt.asset.management.POJO.DTO.ProcureQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.ProcureDetailVO;
import com.fjyt.asset.management.POJO.VO.ProcureVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.mapper.DingDingMapper;
import com.fjyt.asset.management.mapper.ProcureMapper;
import com.fjyt.asset.management.service.ProcureService;
import com.fjyt.asset.management.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author keQiLong
 * @date 2023年05月19日 9:57
 */
@Service
public class ProcureServiceImpl implements ProcureService {

    @Autowired
    private ProcureMapper procureMapper;
    @Autowired
    private DingDingMapper dingDingMapper;
    @Autowired
    private AssetMapper assetMapper;
    /**
     * 查询采购信息列表
     * @param procureQueryDTO
     * @return
     */
    @Override
    public List<ProcureVO> getProcureList(ProcureQueryDTO procureQueryDTO) {
        List<ProcureVO> procureList = procureMapper.getProcureList(procureQueryDTO);
        procureList.forEach(procureVO -> {
            List<String> stringList = new ArrayList<>();
            List<DingDingDTO> dingDingDTOS = JSONArray.parseArray(procureVO.getProcureAssets(), DingDingDTO.class);
            dingDingDTOS.forEach(dingDingDTO -> {
                List<Map<String, String>> rowValue = dingDingDTO.getRowValue();
                String assetName = rowValue.get(0).get("value");
                stringList.add(assetName);
            });
            String assetNames = stringList.toString();
            procureVO.setAssetNames(assetNames.substring(1,assetNames.length()-1));
        });
        return procureList;
    }

    /**
     * 根据id查询采购信息详情
     * @param id
     * @return
     */
    @Override
    public R getProcureDetail(Long id) {
        ProcureDetailVO procureDetail = procureMapper.getProcureDetail(id);
        String procureAssets = procureDetail.getProcureAssets();
        List<DingDingDTO> dingDingDTOS = JSONArray.parseArray(procureAssets, DingDingDTO.class);
        List<Map<String,String>> mapList = new ArrayList<>();
        dingDingDTOS.forEach(dingDingDTO -> {
            List<Map<String, String>> rowValue = dingDingDTO.getRowValue();
            String assetName = rowValue.get(0).get("value");
            String specifications = rowValue.get(1).get("value");
            String num = rowValue.get(2).get("value");
            String unit = rowValue.get(3).get("value");
            String price = rowValue.get(4).get("value");
            System.out.println("物品名称："+assetName+"；规格："+specifications+"；数量："+num+"；单位："+unit+"；价格："+price);
            Map<String,String> map = new HashMap<>();
            map.put("assetName",assetName);
            map.put("assetSpecifications",specifications);
            map.put("num",num);
            map.put("unit",unit);
            map.put("price",price);
            mapList.add(map);
        });
        procureDetail.setMapList(mapList);
        return R.ok(procureDetail);
    }

    /**
     * 新增采购信息
     * @param procure
     */
    @Override
    public void addProcure(Procure procure) {
        procureMapper.addProcure(procure);
    }

    /**
     * 采购验收
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R checkAndAccept(Long id) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        // 修改状态 验收人
        Procure procure = new Procure();
        procure.setId(id);
        procure.setCheckUser(userName);
        procure.setCheckTime(DateUtils.getNowDate());
        procure.setStatus("3");
        procureMapper.updateProcure(procure);

        Date date = DateUtils.getNowDate();

        // 获取procure_assets
        String procureAssets = procureMapper.getProcureDetail(id).getProcureAssets();
        List<DingDingDTO> dingDingDTOS = JSONArray.parseArray(procureAssets, DingDingDTO.class);

        dingDingDTOS.forEach(dingDingDTO -> {
            List<Map<String, String>> rowValue = dingDingDTO.getRowValue();

            String assetName = rowValue.get(0).get("value");
            String specifications = rowValue.get(1).get("value");
            int num = Integer.valueOf(rowValue.get(2).get("value"));
            String unit = rowValue.get(3).get("value");
            Float price = Float.valueOf(rowValue.get(4).get("value"));
            //System.out.println("物品名称："+assetName+"；规格："+specifications+"；数量："+num+"；单位："+unit+"；价格："+price);

            for (int i=0;i<num;i++){
                String assetCode = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.ASSET_ZC);
                Asset asset = new Asset();
                asset.setAssetCode(assetCode);
                asset.setAssetName(assetName);
                asset.setAssetSpecifications(specifications);
                asset.setAssetUnit(unit);
                asset.setAssetPrice(price);
                asset.setCreateTime(date);
                asset.setUpdateTime(date);
                asset.setCreateWay("1");
                assetMapper.add(asset);
            }
        });
        return null;
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
        procureMapper.updateStatus(status,code);
    }

    /**
     * 关联钉钉新增采购单
     * @param procureAssets
     * @param procureUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProcureByDingDing(Procure procure,String processInstanceId) {
        System.out.println("关联钉钉创建采购单");
        String code = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.WAREHOUSE_CG);
        // 新增采购与审批关联表
        ManagementDingDing managementDingDing = new ManagementDingDing();
        managementDingDing.setCode(code);
        managementDingDing.setProcessInstanceId(processInstanceId);
        dingDingMapper.addManagementDingDing(managementDingDing);
        // 新增采购单
        procure.setProcureCode(code);
        procure.setProcureTime(DateUtils.getNowDate());
        procure.setCreateWay("1");
        procure.setCreateTime(DateUtils.getNowDate());
        procure.setUpdateTime(DateUtils.getNowDate());
        procureMapper.addProcure(procure);
    }
}

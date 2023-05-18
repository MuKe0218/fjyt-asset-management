package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.AssetWarehousing;
import com.fjyt.asset.management.POJO.DTO.WarehouseQueryDTO;
import com.fjyt.asset.management.POJO.DTO.WarehousingDTO;
import com.fjyt.asset.management.POJO.DTO.WarehousingQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.POJO.VO.WarehousingDetailVO;
import com.fjyt.asset.management.POJO.VO.WarehousingVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.mapper.WarehousingMapper;
import com.fjyt.asset.management.service.WarehousingService;
import com.fjyt.asset.management.utils.DateUtils;
import com.fjyt.asset.management.utils.JwtUtils;
import com.fjyt.asset.management.utils.SerialNumberUtils;
import com.fjyt.asset.management.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月18日 16:13
 */
@Service
public class WarehousingServiceImpl implements WarehousingService {

    @Autowired
    private WarehousingMapper warehousingMapper;
    @Autowired
    private AssetMapper assetMapper;
    /**
     * 获取入库单列表
     * @param warehouseQueryDTO
     * @return
     */
    @Override
    public List<WarehousingVO> getWarehousingList(WarehousingQueryDTO warehousingQueryDTO) {
        List<WarehousingVO> warehousingList = warehousingMapper.getWarehousingList(warehousingQueryDTO);
        warehousingList.forEach(warehousingVO -> {
            List<String> strings = Arrays.asList(warehousingVO.getAssetCodes().replace(" ","").split(","));
            List<String> assetNameList = assetMapper.getAssetNameList(strings);
            String assetNames = assetNameList.toString();
            warehousingVO.setAssetNames(assetNames.substring(1,assetNames.length()-1));
        });
        return warehousingList;
    }

    /**
     * 根据id获取入库单详情
     * @param id
     * @return
     */
    @Override
    public R getWarehousingDetail(Long id) {
        WarehousingDetailVO warehousingDetail = warehousingMapper.getWarehousingDetail(id);
        List<String> strings = Arrays.asList(warehousingDetail.getAssetCodes().replace(" ","").split(","));
        List<AssetVO> assetByCodes = assetMapper.getAssetByCodes(strings);
        warehousingDetail.setWarehousingAssetList(assetByCodes);
        return R.ok(warehousingDetail);
    }

    /**
     * 新增入库单
     * @param warehousingDTO
     */
    @Override
    public void addWarehousing(WarehousingDTO warehousingDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        String warehousingCode = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.WAREHOUSE_RK);
        AssetWarehousing assetWarehousing = new AssetWarehousing();
        assetWarehousing.setWarehousingCode(warehousingCode);
        assetWarehousing.setAssetCodes(warehousingDTO.getAssetCodes());
        assetWarehousing.setWarehousingTime(DateUtils.getNowDate());
        assetWarehousing.setCreateUser(userName);
        assetWarehousing.setCreateTime(DateUtils.getNowDate());
        assetWarehousing.setUpdateUser(userName);
        assetWarehousing.setUpdateTime(DateUtils.getNowDate());
        warehousingMapper.addWarehousing(assetWarehousing);
    }
}

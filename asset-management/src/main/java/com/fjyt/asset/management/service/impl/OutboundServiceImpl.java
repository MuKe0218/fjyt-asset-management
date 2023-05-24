package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.AssetOutbound;
import com.fjyt.asset.management.POJO.DO.AssetWarehousing;
import com.fjyt.asset.management.POJO.DTO.OutboundDTO;
import com.fjyt.asset.management.POJO.DTO.OutboundQueryDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetVO;
import com.fjyt.asset.management.POJO.VO.OutboundDetailVO;
import com.fjyt.asset.management.POJO.VO.OutboundVO;
import com.fjyt.asset.management.constant.SerialNumberConstants;
import com.fjyt.asset.management.mapper.AssetMapper;
import com.fjyt.asset.management.mapper.OutboundMapper;
import com.fjyt.asset.management.service.OutboundService;
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
 * @date 2023年05月18日 15:30
 */
@Service
public class OutboundServiceImpl implements OutboundService {
    @Autowired
    private OutboundMapper outboundMapper;
    @Autowired
    private AssetMapper assetMapper;
    /**
     * 获取出库单列表
     * @param outboundQueryDTO
     * @return
     */
    @Override
    public List<OutboundVO> getOutboundList(OutboundQueryDTO outboundQueryDTO) {
        List<OutboundVO> outboundList = outboundMapper.getOutboundList(outboundQueryDTO);
        outboundList.forEach( outboundVO -> {
            List<String> strings = Arrays.asList(outboundVO.getAssetCodes().replace(" ","").split(","));
            List<String> assetNameList = assetMapper.getAssetNameList(strings);
            String assetNames = assetNameList.toString();
            outboundVO.setAssetNames(assetNames.substring(1,assetNames.length()-1));
        });
        return outboundList;
    }

    /**
     * 根据id获取出库单详情
     * @param id
     * @return
     */
    @Override
    public R getOutboundDetail(Long id) {
        OutboundDetailVO outboundDetail = outboundMapper.getOutboundDetail(id);
        List<String> strings = Arrays.asList(outboundDetail.getAssetCodes().replace(" ","").split(","));
        List<AssetVO> assetByCodes = assetMapper.getAssetByCodes(strings);
        outboundDetail.setOutboundAssetList(assetByCodes);
        return R.ok(outboundDetail);
    }

    /**
     * 新增出库单
     * @param outboundDTO
     */
    @Override
    public void addOutbound(OutboundDTO outboundDTO) {
        String outboundCode = new SerialNumberUtils().createSerialNumber(SerialNumberConstants.WAREHOUSE_CK);
        AssetOutbound assetOutbound = new AssetOutbound();
        assetOutbound.setOutboundCode(outboundCode);
        assetOutbound.setAssetCodes(outboundDTO.getAssetCodes());
        assetOutbound.setOutboundTime(DateUtils.getNowDate());
        assetOutbound.setCreateTime(DateUtils.getNowDate());
        assetOutbound.setUpdateTime(DateUtils.getNowDate());
        outboundMapper.addOutbound(assetOutbound);
    }
}

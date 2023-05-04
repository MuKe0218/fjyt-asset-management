package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.AssetClassify;
import com.fjyt.asset.management.POJO.DTO.AssetClassifyDTO;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.POJO.VO.AssetClassifyVO;
import com.fjyt.asset.management.constant.Constants;
import com.fjyt.asset.management.exception.ClassifyException;
import com.fjyt.asset.management.mapper.AssetClassifyMapper;
import com.fjyt.asset.management.service.AssetClassifyService;
import com.fjyt.asset.management.utils.DateUtils;
import com.fjyt.asset.management.utils.JwtUtils;
import com.fjyt.asset.management.utils.StringUtils;
import com.fjyt.asset.management.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月04日 10:31
 * 分类业务实现层
 */
@Service
public class AssetClassifyServiceImpl implements AssetClassifyService {
    @Autowired
    private AssetClassifyMapper assetClassifyMapper;
    /**
     * 查询分类列表
     * @param assetClassify
     * @return
     */
    @Override
    public List<AssetClassifyVO> assetClassifyList(AssetClassify assetClassify) {

        return assetClassifyMapper.assetClassifyList(assetClassify);
    }

    /**
     * 新增分类
     * @param assetClassifyDTO
     * @return
     */
    @Override
    public R assetClassifyAdd(AssetClassifyDTO assetClassifyDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetClassify assetClassify = new AssetClassify();
        assetClassify.setClassifyName(assetClassifyDTO.getClassifyName());
        assetClassify.setOrderNum(assetClassifyDTO.getOrderNum());
        assetClassify.setCreateUser(userName);
        assetClassify.setCreateTime(DateUtils.getNowDate());
        assetClassify.setUpdateUser(userName);
        assetClassify.setUpdateTime(DateUtils.getNowDate());
        if (!StringUtils.isNotNull(assetClassifyDTO.getParentId())){
            assetClassify.setParentId(assetClassifyDTO.getParentId());
            String ancestors = assetClassifyMapper.getAncestorsByClassifyId(assetClassifyDTO.getParentId());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(ancestors).append(",").append(assetClassifyDTO.getParentId());
            assetClassify.setAncestors(stringBuffer.toString());
        }
        //校验分类是否已存在
        AssetClassify checkAssetClassify = this.checkAssetClassifyByName(assetClassifyDTO.getClassifyName());
        if (StringUtils.isNotNull(checkAssetClassify)){
            throw new ClassifyException(Constants.FAIL,"此分类已存在");
        }
        assetClassifyMapper.assetClassifyAdd(assetClassify);
        return R.ok("新增分类成功");
    }

    /**
     * 校验分类是否已存在
     * @param classifyName
     * @return
     */
    @Override
    public AssetClassify checkAssetClassifyByName(String classifyName) {
        return assetClassifyMapper.checkAssetClassifyByName(classifyName);
    }

    /**
     * 删除分类
     * @param classifyId
     * @return
     */
    @Override
    public R assetClassifyDel(Long classifyId) {
        //判断分类是否被使用
        //删除分类
        assetClassifyMapper.assetClassifyDel(classifyId);
        return R.ok("删除成功");
    }

}

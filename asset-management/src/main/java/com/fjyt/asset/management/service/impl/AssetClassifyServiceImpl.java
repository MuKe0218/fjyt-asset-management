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
import org.springframework.transaction.annotation.Transactional;

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
     * 根据分类id获取分类详情
     * @param classifyId
     * @return
     */
    @Override
    public R assetClassifyByClassifyId(Long classifyId) {
        return R.ok(assetClassifyMapper.assetClassifyByClassifyId(classifyId));
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
     * 新增分类
     * @param assetClassifyDTO
     * @return
     */
    @Override
    public R assetClassifyAdd(AssetClassifyDTO assetClassifyDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetClassify assetClassify = new AssetClassify();
        //校验分类名称是否已存在
        AssetClassify checkAssetClassify = this.checkAssetClassifyByName(assetClassifyDTO.getClassifyName());
        if (StringUtils.isNotNull(checkAssetClassify)){
            throw new ClassifyException(Constants.FAIL,"此分类已存在");
        }
        //判断添加的分类是否有父类
        if (StringUtils.isNotNull(assetClassifyDTO.getParentId())){
            String ancestors = ancestorsMontage(assetClassifyDTO.getParentId());
            assetClassify.setParentId(assetClassifyDTO.getParentId());
            assetClassify.setAncestors(ancestors);
        }
        assetClassify.setClassifyName(assetClassifyDTO.getClassifyName());
        assetClassify.setOrderNum(assetClassifyDTO.getOrderNum());
        assetClassify.setCreateUser(userName);
        assetClassify.setCreateTime(DateUtils.getNowDate());
        assetClassify.setUpdateUser(userName);
        assetClassify.setUpdateTime(DateUtils.getNowDate());
        assetClassifyMapper.assetClassifyAdd(assetClassify);
        return R.ok("新增分类成功");
    }

    /**
     * 编辑分类信息
     * @param assetClassifyDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R assetClassifyUpdate(AssetClassifyDTO assetClassifyDTO) {
        String userName = JwtUtils.getUserName(TokenUtils.getToken());
        AssetClassify assetClassify = new AssetClassify();
        // 根据分类id查询修改前分类对象
        AssetClassifyVO oldAssetClassify = assetClassifyMapper.assetClassifyByClassifyId(assetClassifyDTO.getClassifyId());
        //根据分类名称查询分类
        AssetClassify checkAssetClassify = this.checkAssetClassifyByName(assetClassifyDTO.getClassifyName());
        // 判断修改名称是否已存在
        if (!oldAssetClassify.getClassifyName().equals(assetClassifyDTO.getClassifyName()) && StringUtils.isNotNull(checkAssetClassify)){
            throw new ClassifyException(Constants.FAIL,"此分类已存在");
        }
        //判断添加的分类是否有父类
        if (StringUtils.isNotNull(assetClassifyDTO.getParentId())){
            String ancestors = ancestorsMontage(assetClassifyDTO.getParentId());
            assetClassify.setParentId(assetClassifyDTO.getParentId());
            assetClassify.setAncestors(ancestors);
        }
        assetClassify.setClassifyId(assetClassifyDTO.getClassifyId());
        assetClassify.setClassifyName(assetClassifyDTO.getClassifyName());
        assetClassify.setOrderNum(assetClassifyDTO.getOrderNum());
        assetClassify.setUpdateUser(userName);
        assetClassify.setUpdateTime(DateUtils.getNowDate());
        assetClassifyMapper.assetClassifyUpdate(assetClassify);
        return R.ok("编辑成功");
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

    /**
     * 祖籍列表拼接
     * @param parentId
     * @return
     */
    public String ancestorsMontage(Long parentId){
        String ancestors = assetClassifyMapper.getAncestorsByClassifyId(parentId);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ancestors).append(",").append(parentId);
        return stringBuffer.toString();
    }

}

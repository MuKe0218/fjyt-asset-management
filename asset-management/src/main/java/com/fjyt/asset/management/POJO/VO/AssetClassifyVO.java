package com.fjyt.asset.management.POJO.VO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月04日 10:44
 */
@Data
public class AssetClassifyVO {
    /**
     * 分类ID
     */
    private Long classifyId;
    /**
     * 分类父ID
     */
    private Long parentId;
    /**
     * 分类祖籍列表
     */
    private String ancestors;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 分类显示排序
     */
    private int orderNum;
    /**
     * 分类创建人
     */
    private String createUser;
    /**
     * 分类创建时间
     */
    private Date createTime;
    /**
     * 分类修改人
     */
    private String updateUser;
    /**
     * 分类修改时间
     */
    private Date updateTime;
    /**
     * 分类子分类
     */
    private List<AssetClassifyVO> children;
}

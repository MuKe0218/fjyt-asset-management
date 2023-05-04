package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author keQiLong
 * @date 2023年05月04日 10:06
 * 资产分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetClassify implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 分类删除标志 0未删除 1已删除
     */
    private String delFlag;
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
}

package com.fjyt.asset.management.POJO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月04日 10:03
 * 资产分类dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetClassifyDTO {
    /**
     * 分类id
     */
    private Long classifyId;
    /**
     * 分类父ID
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 分类显示顺序
     */
    private int orderNum;
}

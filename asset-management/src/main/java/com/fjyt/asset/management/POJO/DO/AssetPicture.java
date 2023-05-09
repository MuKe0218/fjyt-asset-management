package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author keQiLong
 * @date 2023年05月06日 8:49
 * 资产图片关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetPicture implements Serializable {
    private static final long serializable = 1L;
    /**
     * 资产流水号
     */
    private String assetCode;
    /**
     * 图片路径
     */
    private String picturePath;
}

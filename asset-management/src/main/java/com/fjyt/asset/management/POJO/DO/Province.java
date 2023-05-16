package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author keQiLong
 * @date 2023年05月15日 17:56
 * 省份
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province implements Serializable {
    private static final long serializable = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 省份名称
     */
    private String name;
    /**
     * 省份id
     */
    private String provinceId;

    public  Province(String provinceId){
        this.provinceId = provinceId;
    }
}

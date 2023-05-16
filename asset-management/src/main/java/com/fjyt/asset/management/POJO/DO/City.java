package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author keQiLong
 * @date 2023年05月15日 17:58
 * 城市
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {
    private static final long serializable = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 城市id
     */
    private String cityId;
    /**
     * 省份id
     */
    private String provinceId;

    public City(String cityId){
        this.cityId = cityId;
    }
}

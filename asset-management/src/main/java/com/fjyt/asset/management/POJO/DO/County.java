package com.fjyt.asset.management.POJO.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keQiLong
 * @date 2023年05月16日 8:38
 * 区县
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class County {
    /**
     * id
     */
    private Long id;
    /**
     * 区县名称
     */
    private String name;
    /**
     * 区县id
     */
    private String countyId;
    /**
     * 城市id
     */
    private String cityId;

    public County(String countyId){
        this.countyId = countyId;
    }
}

package com.fjyt.asset.management.service;

import com.fjyt.asset.management.POJO.DO.City;
import com.fjyt.asset.management.POJO.DO.County;
import com.fjyt.asset.management.POJO.DO.Province;
import com.fjyt.asset.management.POJO.R;

/**
 * @author keQiLong
 * @date 2023年05月16日 8:49
 */
public interface AddressService {
    /**
     * 获取省份信息列表
     * @param province
     * @return
     */
    public R getProvince(Province province);

    /**
     * 获取城市信息列表
     * @param city
     * @return
     */
    public R getCity(City city);

    /**
     * 获取区县信息列表
     * @param county
     * @return
     */
    public R getCounty(County county);
}

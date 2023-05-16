package com.fjyt.asset.management.service.impl;

import com.fjyt.asset.management.POJO.DO.City;
import com.fjyt.asset.management.POJO.DO.County;
import com.fjyt.asset.management.POJO.DO.Province;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.mapper.AddressMapper;
import com.fjyt.asset.management.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author keQiLong
 * @date 2023年05月16日 8:50
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    /**
     * 获取省份信息列表
     * @param province
     * @return
     */
    @Override
    public R getProvince(Province province) {
        return R.ok(addressMapper.getProvince(province));
    }

    /**
     * 获取城市信息列表
     * @param city
     * @return
     */
    @Override
    public R getCity(City city) {
        return R.ok(addressMapper.getCity(city));
    }

    /**
     * 获取区县信息列表
     * @param county
     * @return
     */
    @Override
    public R getCounty(County county) {
        return R.ok(addressMapper.getCounty(county));
    }
}

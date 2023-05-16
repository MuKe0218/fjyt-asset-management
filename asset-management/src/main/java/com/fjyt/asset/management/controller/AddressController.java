package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DO.City;
import com.fjyt.asset.management.POJO.DO.County;
import com.fjyt.asset.management.POJO.DO.Province;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keQiLong
 * @date 2023年05月15日 17:53
 * 地址Controller
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    /**
     * 获取省份
     * @param province
     * @return
     */
    @GetMapping("/province")
    public R getProvince(Province province){
        return addressService.getProvince(province);
    }

    /**
     * 获取城市
     * @param city
     * @return
     */
    @GetMapping("/city")
        public R getCity(City city){
        return addressService.getCity(city);
    }

    /**
     * 获取区县
     * @param county
     * @return
     */
    @GetMapping("/county")
    public R getCounty(County county){
        return addressService.getCounty(county);
    }
}

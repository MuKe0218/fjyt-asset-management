package com.fjyt.asset.management.mapper;

import com.fjyt.asset.management.POJO.DO.City;
import com.fjyt.asset.management.POJO.DO.County;
import com.fjyt.asset.management.POJO.DO.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author keQiLong
 * @date 2023年05月16日 8:46
 */
@Mapper
public interface AddressMapper {
    /**
     * 获取省份信息列表
     * @param province
     * @return
     */
    public List<Province> getProvince(Province province);

    /**
     * 获取城市信息列表
     * @param city
     * @return
     */
    public List<City> getCity(City city);

    /**
     * 获取区县信息列表
     * @param county
     * @return
     */
    public List<County> getCounty(County county);
}
